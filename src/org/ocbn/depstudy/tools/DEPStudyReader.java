package org.ocbn.depstudy.tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import org.ocbn.depstudy.model.AttValLabel;
import org.ocbn.depstudy.model.Encounter;
import org.ocbn.depstudy.model.ModelCV;
import org.ocbn.depstudy.model.ModelContainer;
import org.ocbn.depstudy.model.Params;
import org.ocbn.depstudy.model.Patient;
import org.ocbn.depstudy.model.PatientDemo;
import org.ocbn.depstudy.model.ProtParams;
import org.ocbn.depstudy.model.Protein;
import org.ocbn.depstudy.model.Sample;
import org.ocbn.depstudy.util.GenUtil;

/**
 * A custom reader for the spreadsheet generated. 
 * 
 * To-DO-LIST: Consider setting fileSrc and saveFlag as local atts, and separating
 * the readEntries () call from the constructor. 
 * 
 * @author ocbn
 */

public class DEPStudyReader {
       
    //attributes iterated over
    private Patient p;
    private Encounter e;
    private Sample s; 
    private Protein protein; 
    private BufferedReader br;
    
    protected DEPStudyReader (String fileName, String mappingFileName) 
                             throws IOException {
        
        GenUtil.validateString(fileName);
        GenUtil.validateString (mappingFileName);
        ModelCV.initializeLabelsFile(mappingFileName);
        br = new BufferedReader (new FileReader (fileName));
    }
    
    protected final void readEntries () throws IOException {
    
        String line; 
        String [] headersArr = null;
        int cnt = 0, defValCnt = 0;
        while ((line = br.readLine()) != null) {
            this.p = null;
            this.e = null;
            this.s = null;
            this.protein = null;
            line = line.trim();
            String [] tempArr = parseCSV (line);
            //skip empty trailing lines
            if (tempArr.length == 0) {
                continue;
            }
            if (headersArr == null) {
                headersArr = tempArr;
                continue;
            }
            for (int i = 0; i < tempArr.length; i++) {
                if (tempArr [i] == null || tempArr [i].trim ().length () == 0) {
                    continue;
                }
                tempArr [i] = tempArr [i].trim();
                if (tempArr [i].equals (GenUtil.NA2)) {
                    tempArr [i] = GenUtil.NA;
                    defValCnt++;
                }
                readDEPStudyAtt (headersArr [i], tempArr [i], i);
            }
            cnt++;
        }
        generatePatientIDs ();
        System.out.println ("Added : " + cnt + " entries from source.");
        System.out.println ("Default values (" + GenUtil.NA + "): " + defValCnt);
    }
    
    public void readDEPStudyAtt (String attName, String attVal, int i) {
        
        //handy varialbles
        AttValLabel avl;
        PatientDemo pd;

        switch (attName) {
            //patient ID/Encounter Type, format XX, XX-2, XX-3
            case ModelCV.PID:
                parsePID (attVal);
                return;
            case ModelCV.BASE:
                return;
            //PatientDemo atts.
            case ModelCV.DEPRESSED: 
                avl = getCurrentCG ();
                avl.setVal(attVal);
                return;
            case ModelCV.CG:
                avl = getCurrentCG ();
                avl.setValLabel (attVal);
                return;
            case ModelCV.COL_CGH:
                pd = getCurrentPD ();
                pd.setClinGroupHAMD(attVal);
                return;
            case ModelCV.AGE:
                pd = getCurrentPD ();
                pd.setAge(new Integer (attVal).intValue());
                return;
            case ModelCV.GEN:
                pd = getCurrentPD ();
                AttValLabel gender = pd.getGender ();
                if (gender == null) {
                    gender = new AttValLabel();
                    pd.setGender (gender);
                }
                gender.setVal(attVal);
                gender.setValLabel(ModelCV.getMapping(attName, attVal));
                return;
            case ModelCV.ETH:
                pd = getCurrentPD ();
                AttValLabel ethnic = pd.getEthnic();
                if (ethnic == null) {
                    ethnic = new AttValLabel();
                    pd.setEthnic(ethnic);
                }
                ethnic.setVal(attVal);
                ethnic.setValLabel(ModelCV.getMapping(attName, attVal));
                return;
            //sample info
            case ModelCV.SN:
                if (s == null) {
                    s = new Sample();
                }
                s.setName(attVal);
                return;
            case ModelCV.DO:
                if (s == null) {
                    s = new Sample();
                }
                s.setDataSetOrder (new Integer (attVal).intValue());
                return;
            case ModelCV.BLOCK:
                if (s == null) {
                    s = new Sample();
                }
                s.setBlockNum(new Integer (attVal).intValue());
                return;
            case ModelCV.BO:
                if (s == null) {
                    s = new Sample();
                }
                s.setBlockOrder(new Integer (attVal).intValue());
                return;
            case ModelCV.SI:
                if (s == null) {
                    s = new Sample();
                }
                s.setID(attVal);
                //only now, add to container.
                ModelContainer.setSample (s);
                return;
        }
        parseParams(attName, attVal);    
        
    }
    
    private void parseParams (String attName, String attVal) {

        AttValLabel avl = new AttValLabel();
        avl.setName(attName);
        avl.setVal(attVal);
        String label = ModelCV.getMapping(attName, attVal); //not needed for lab params
        if (label != null) {
            avl.setValLabel(label);
        }
        Params param;
        if (ModelCV.isValidClinParam (attName)) {
            param = ModelContainer.getClinParam(p, e);
            //section to handle inconsistent cancer type codes.
            if (attName.equals (ModelCV.CT)) {  
                if (!attVal.trim ().matches("^-?\\d+$")) {
                    System.err.println ("Warning: Invalid cancer type code replaced"
                                        + " with 9996: " + attVal);
                    attVal = ModelCV.CT_OTHER; 
                    label = ModelCV.getMapping(attName, ModelCV.CT_OTHER);
                    avl.setVal(attVal);
                    avl.setValLabel(label);
                }
            }
            //end section
            param.addParam(avl);
        } else if (ModelCV.isValidLabParam(attName)) {
            param = ModelContainer.getLabParam(p, e);
            param.addParam(avl);
        } else {
            readDEPStudyProteinAtt (attName, attVal);
        }
    }
    
    //utility methods
    private void parsePID (String attVal) {
        
        int index = attVal.indexOf(GenUtil.HYPHEN);
        if (index < 0) {                            //first encounter
            p = ModelContainer.getPatient (attVal);
            e = ModelContainer.getEncounter(ModelCV.ENCOUNTER_BASELINE);
        } else {
            String secID = attVal.substring (0, index);
            p = ModelContainer.getPatient (secID);
            if (attVal.substring(index + 1,
                attVal.length()).contains(ModelCV.ENCOUNTER_2)) {
                e = ModelContainer.getEncounter(ModelCV.ENCOUNTER_2);
            } else if (attVal.substring(index + 1, 
                       attVal.length()).contains(ModelCV.ENCOUNTER_3)) {
                e = ModelContainer.getEncounter(ModelCV.ENCOUNTER_3);
            }
        }    
    }
    
    private AttValLabel getCurrentCG () {
        
        PatientDemo pd = getCurrentPD();
        AttValLabel cg = pd.getClinGroup ();
        if (cg == null) {
            cg = new AttValLabel();
            pd.setClinGroup(cg);
        }
        
        return cg;
    } 
    
    private PatientDemo getCurrentPD () {
        
        PatientDemo pd = p.getPatientDemo();
        if (pd == null) {
            pd = new PatientDemo();
            p.setPatientDemo(pd);
        }
        
        return pd;
    }
    
    private void readDEPStudyProteinAtt (String attName, String attVal) {
      
       //get measurement type 
       int index = attName.lastIndexOf(GenUtil.DOT);
       if (index < 0) {
           throw new IllegalArgumentException ("Peptide name in the wrong format: "
                     + attName);
       }
       String measureType = attName.substring(index+1);
       //fill it in
       String proteinName = attName.substring(0, index);
       setProteinName (proteinName);
       ProtParams pp = ModelContainer.getProtParam (p, e, s, protein);
       setPParam (pp, measureType, attVal);
    }

    private void setPParam (ProtParams pp, String measureType, String attVal) {
        
        switch (measureType) {
            case ModelCV.MT_AREA:
                pp.setPepArea(attVal);
                break;
            case ModelCV.MT_SN:
                pp.setPepSToN(attVal);
                break;
            case ModelCV.MT_HT:
                pp.setPepHt(attVal);
                break;
            default:
                throw new IllegalArgumentException ("Unknown peptide measure: " +
                        measureType);
        }
    }
   
    private void setProteinName (String str) {
        
       this.protein = ModelContainer.getProtein(str);
       //...set peptide name...    
    }
    
    private void generatePatientIDs () {
        
        TreeMap <Sample, Patient> spMap = new TreeMap ();
        Iterator iterator = ModelContainer.getProtParams().keySet().iterator();
        while (iterator.hasNext()) {
            ProtParams pp = ModelContainer.getProtParams().get((String)iterator.next());
            //IDs are generated at first encounter
            if (!pp.getEncounter().getType().equals(ModelCV.ENCOUNTER_BASELINE)) {
                continue;
            }
            spMap.put (pp.getSample(), pp.getPatient());
        }
        iterator = spMap.keySet().iterator();
        int suffix = 1;
        while (iterator.hasNext()) {
            Sample currentSample = (Sample)iterator.next();
            Patient currentPatient = spMap.get (currentSample);
            currentPatient.setPatientID (suffix++);
        }
    }
    
    //basic CSV parser
    private String [] parseCSV (String line) {
        
        ArrayList  <String> tempList= new ArrayList <> ();
        StringBuilder temp = new StringBuilder ();
        boolean stringFlag = false;
        for (int i = 0; i < line.length (); i++) {
            if (line.charAt(i) == '\"') {
                if (stringFlag == false) {         //starting a string literal
                    stringFlag = true;             
                } else {                           //ending a string literal
                    stringFlag = false;
                    if (temp.length() > 0) {       //anything between the quotes
                        tempList.add (temp.toString());
                        temp.delete(0, temp.length ());
                    }
                }
                continue;
            }
            if (line.charAt (i) == GenUtil.COMMA.charAt(0) && 
                stringFlag != true) {               //end a field, comma not in a string literal
                if (temp.length() > 0) {            //anything between the commas
                    tempList.add (temp.toString());
                    temp.delete(0, temp.length ());
                } 
                continue;
            }
            temp.append (line.charAt (i));
        }
        if (temp.length() > 0) {                    //leftover field
            tempList.add (temp.toString());
            temp.delete(0, temp.length ());
        } 

        return tempList.toArray(new String [tempList.size ()]);
    }
}

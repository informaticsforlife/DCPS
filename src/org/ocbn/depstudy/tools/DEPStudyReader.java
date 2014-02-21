package org.ocbn.depstudy.tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.ocbn.depstudy.model.ModelCV;
import org.ocbn.depstudy.model.ModelContainer;
import org.ocbn.depstudy.model.ProtParams;
import org.ocbn.depstudy.model.Protein;
import org.ocbn.depstudy.util.GenUtil;

/**
 * A custom reader for the spreadsheet generated. 
 * 
 * To-DO-LIST: Consider setting fileSrc and saveFlag as local atts, and separating
 * the readEntries () call from the constructor. 
 * 
 * @author ocbn
 */

public class DEPStudyReader extends DEPStudyHandler {
       
    private String tempArr [];
    
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
        int cnt = 0;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            tempArr = parseCSV (line);
            //skip empty trailing lines
            if (tempArr.length == 0) {
                continue;
            }
            if (headersArr == null) {
                headersArr = tempArr;
                continue;
            }
            for (int i = 0; i < tempArr.length; i++) {
                tempArr [i] = tempArr [i].trim();
                if (tempArr [i] == null || tempArr [i].length () == 0) {
                    continue;
                }
                readDEPStudyAtt (headersArr [i], tempArr [i], i);
            }
            //ModelContainer.addDrug (nDrug);
            cnt++;
        }
        System.out.println ("Added : " + cnt + " entries from source.");
    }
    
    public void readDEPStudyAtt (String attName, String attVal, int i) {
        
       //if parsing proteomics info 
       if (i > 102) {
           readDEPStudyProteinAtt (attName, attVal);
       }
       
       
    }
    
    public void readDEPStudyProteinAtt (String attName, String attVal) {
        
       //get measurement type 
       int index = attName.lastIndexOf(GenUtil.DOT);
       if (index < 0) {
           throw new IllegalArgumentException ("Peptide name in the wrong format: "
                     + attName);
       }
       String measureType = attName.substring(index+1);
       
       //fill it in
       String proteinName = attName.substring(0, index);
       Protein p = ModelContainer.getProtein(proteinName);
       ProtParams pp = ModelContainer.getProtParam (tempArr [9]); //sampleID
       pp.setProtein (p);
       setPParam (pp, measureType, attVal);
GenUtil.getDefaultLog ().println (proteinName + " " + measureType + "   " + tempArr [9]);
    }

    private void setPParam (ProtParams pp, String measureType, String attVal) {
        
        final String MT_AREA = "area";
        final String MT_SN = "sn";
        final String MT_HT = "height";
        switch (measureType) {
            case MT_AREA:
                pp.setPepArea(attVal);
                break;
            case MT_SN:
                pp.setPepSToN(attVal);
                break;
            case MT_HT:
                pp.setPepHt(attVal);
                break;
            default:
                throw new IllegalArgumentException ("Unknown peptide measure: " +
                        measureType);
        }
    }
   
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

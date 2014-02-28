package org.ocbn.depstudy.model;

import java.util.TreeMap;
import org.ocbn.depstudy.util.GenUtil;

/**
 * Container class for all entities. Works hand in hand with the reader/writer.
 * ProtParam could also use PatientID+EncounterID as unique ID
 * 
 * @author ocbn
 */

public class ModelContainer {
   
    //Entities
    private static TreeMap <String, Patient> patientMap; 
    private static TreeMap <String, Encounter> encounterMap;
    private static TreeMap <String, Sample> sampleMap;
    private static TreeMap <String, Protein> proteinMap;
    //Joint entities
    private static TreeMap <String, Params> clinMap;
    private static TreeMap <String, Params> labMap;
    private static TreeMap <String, ProtParams> proteoMap;
    
    static {        
        ModelContainer.patientMap = new TreeMap <> ();
        ModelContainer.encounterMap = new TreeMap <> ();
        ModelContainer.sampleMap = new TreeMap <> ();
        ModelContainer.proteinMap = new TreeMap <> ();
        
        ModelContainer.clinMap = new TreeMap <> ();
        ModelContainer.labMap = new TreeMap <> ();
        ModelContainer.proteoMap = new TreeMap <> ();
    } 
   
    //a combo get-set/add
    public static Patient getPatient (String secondaryID) {
        
        GenUtil.validateString(secondaryID);
        if (patientMap.containsKey (secondaryID)) {
            return patientMap.get (secondaryID);
        }
        Patient p = new Patient ();
        p.setSecondaryID (secondaryID);
        patientMap.put(secondaryID, p);
        
        return p;
    }

    public static Encounter getEncounter (String encounterType) {
        
        GenUtil.validateString(encounterType);
        if (encounterMap.containsKey (encounterType)) {
            return encounterMap.get (encounterType);
        }
        Encounter e = new Encounter ();
        e.setType (encounterType);
        encounterMap.put (encounterType, e);
        
        return e;
    }
        
    public static Protein getProtein (String proteinName) {
        
        GenUtil.validateString(proteinName);
        if (proteinMap.containsKey (proteinName)) {
            return proteinMap.get (proteinName);
        }
        Protein p = new Protein ();
        p.setName (proteinName);
        proteinMap.put(proteinName, p);
        
        return p;
    }
        
    public static Sample getSample (String sampleID) {
        
        GenUtil.validateString(sampleID);
        if (sampleMap.containsKey (sampleID)) {
            return sampleMap.get (sampleID);
        }
        Sample s = new Sample ();
        s.setID (sampleID);
        sampleMap.put(sampleID, s);
        
        return s;
    }

    //overwrite existing, if any, used as needed. 
    public static void setSample (Sample sample) {
        
        GenUtil.validateNotNull(sample);
        ModelContainer.sampleMap.put (sample.getID(), sample);
    }
    
    public static ProtParams getProtParam (Patient p, Encounter e, Sample s, 
                                           Protein protein) {
        
        GenUtil.validateNotNull(p);
        GenUtil.validateNotNull(e);
        GenUtil.validateNotNull(s);        
        GenUtil.validateNotNull(protein);
        String secondaryID = p.getSecondaryID();
        String encounterType = e.getType();
        String proteinName = protein.getName();      
        if (proteoMap.containsKey (secondaryID + GenUtil.AT + encounterType + GenUtil.AT + proteinName)) {
            return proteoMap.get (secondaryID + GenUtil.AT + encounterType + GenUtil.AT + proteinName);
        }
        ProtParams pp = new ProtParams ();
        pp.setPatient (p);
        pp.setEncounter(e);
        pp.setProtein(protein);
        pp.setSample (s);   
        proteoMap.put (secondaryID + GenUtil.AT + encounterType + GenUtil.AT + proteinName, pp);
        
        return pp;
    } 
    
    public static Params getClinParam (Patient p, Encounter e) {
    
        GenUtil.validateNotNull(p);
        GenUtil.validateNotNull (e);
        String secondaryID = p.getSecondaryID();
        String encounterType = e.getType();
        if (clinMap.containsKey (secondaryID + GenUtil.AT + encounterType)) {
            return clinMap.get (secondaryID + GenUtil.AT + encounterType);
        }
        Params cp = new Params ();
        cp.setPatient (p);
        cp.setEncounter(e);
        clinMap.put (secondaryID + GenUtil.AT + encounterType, cp);
        
        return cp;
    } 
          
    public static Params getLabParam (Patient p, Encounter e) {
    
        GenUtil.validateNotNull(p);
        GenUtil.validateNotNull (e);
        String secondaryID = p.getSecondaryID();
        String encounterType = e.getType();
        if (labMap.containsKey (secondaryID + GenUtil.AT + encounterType)) {
            return labMap.get (secondaryID + GenUtil.AT + encounterType);
        }
        Params lp = new Params ();
        lp.setPatient (p);
        lp.setEncounter(e);
        labMap.put (secondaryID + GenUtil.AT + encounterType, lp);
        
        return lp;
    } 
            
    public static TreeMap getPatients () { return ModelContainer.patientMap; }
    
    public static TreeMap getEncounters () { return ModelContainer.encounterMap; }
    
    public static TreeMap getProteins () { return ModelContainer.proteinMap; }
    
    public static TreeMap getSamples () { return ModelContainer.sampleMap; }
    
    public static TreeMap <String, ProtParams> getProtParams () { 
        
        return ModelContainer.proteoMap; 
    } 
    
    public static TreeMap <String, Params> getClinParams () { 
        
        return ModelContainer.clinMap; 
    } 
    
    public static TreeMap <String, Params> getLabParams () { 
        
        return ModelContainer.labMap; 
    } 
}

package org.ocbn.depstudy.model;

import java.util.TreeMap;
import org.ocbn.depstudy.util.GenUtil;

/**
 * The extended join for a patient, and encounter, and clinical/lab parameters 
 * tracked.  
 * 
 * @author ocbn
 */

public class Params extends Persistence {
    
    private Patient patient; 
    private Encounter encounter; 
    private final TreeMap <String, AttValLabel> paramsMap = new TreeMap ();
    
    public Params () { setDBID(ProtParams.SEQ++); }
        
    public void setPatient (Patient nPatient) {
        
        this.patient = nPatient; 
    } 
    
    public void setEncounter (Encounter nEncounter) {
        
        this.encounter = nEncounter;
    }
    
    public Patient getPatient () { return this.patient; }
    
    public Encounter getEncounter () { return this.encounter; }

    public void addParam (AttValLabel nAttVal) {
        
        if (this.paramsMap.containsKey(nAttVal.getName())) {
            System.err.println ("Warning: Overwriting existing param: "
                                + nAttVal.getName ());
        }
        this.paramsMap.put (nAttVal.getName(), nAttVal);
    }
    
    public AttValLabel getParam (String paramName) {
        
        if (this.paramsMap.containsKey(paramName)) {
            return this.paramsMap.get (paramName);
        } 
        System.err.println ("Warning: Requested param not found: " + paramName);
        
        return null;
    } 
    
    public String toStringHeader () {
        
        String temp = "";
        temp += "ID" + GenUtil.TAB + 
                "PatientID" + GenUtil.TAB +
                "EncounterID" + GenUtil.TAB;
        for (String paramName : this.paramsMap.keySet()) {
            temp += paramName + GenUtil.TAB;
            //if such an attribute usually have a label
            if (this.paramsMap.get (paramName).getValLabel() != null) {
                temp += paramName + "_Label" + GenUtil.TAB;
            }
        }
                
        return temp;
    }
    
    @Override
    public String toString () {
        
        String temp = ""; 
        temp += this.getDBID() + GenUtil.TAB + 
                this.getPatient ().getDBID () + GenUtil.TAB + 
                this.getEncounter ().getDBID ();
        for (String paramName : this.paramsMap.keySet()) {
            temp += this.paramsMap.get (paramName).getVal() + GenUtil.TAB;
            //if such an attribute usually have a label.
            if (this.paramsMap.get (paramName).getValLabel() != null) {
                temp +=  this.paramsMap.get (paramName).getValLabel() + GenUtil.TAB;
            }
        }
        
        return temp;
    }
}

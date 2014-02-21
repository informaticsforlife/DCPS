package org.ocbn.depstudy.model;

import java.util.TreeMap;

/**
 * The extended join for a patient, and encounter, and clinical parameters 
 * tracked. 
 * 
 * @author ocbn
 */

public class ClinParams extends Params {
    
    private final TreeMap <String, AttValLabel> clinParamsMap;
    
    public ClinParams () { this.clinParamsMap = new TreeMap (); }
            
    public void addClinParam (AttValLabel nAttVal) {
        
        if (this.clinParamsMap.containsKey(nAttVal.getName())) {
            System.err.println ("Warning: Overwriting existing clinical param: "
                                + nAttVal.getName ());
        }
        this.clinParamsMap.put (nAttVal.getName(), nAttVal);
    }
    
    public AttValLabel getClinParam (String clinParamName) {
        
        if (this.clinParamsMap.containsKey(clinParamName)) {
            return this.clinParamsMap.get (clinParamName);
        } 
        System.err.println ("Warning: Requested clinical param not found: "
                            + clinParamName);
        
        return null;
    } 
    
    @Override
    public String toString () {
        
        String temp = ""; 
        for (String clinParamName : this.clinParamsMap.keySet ()) {
            temp += this.getPatient ().getID () + "\t" +
                    this.getEncounter ().getType() + "\t" +
                    clinParamName + "\t" +
                    clinParamsMap.get (clinParamName).getVal ();
            if (clinParamsMap.get (clinParamName).getValLabel () != null) {
                temp += clinParamsMap.get (clinParamName).getValLabel ();
            } else {
                temp += "NA";
            }
        }
                
        return temp;
    }
}

package org.ocbn.depstudy.model;

import java.util.TreeMap;

/**
 * Similar to the clinical params, but is more for lab results. 
 * 
 * @author ocbn
 */

public class LabParams extends Params {
    
    private final TreeMap <String, AttVal> clinParamsMap;
    
    public LabParams () { this.clinParamsMap = new TreeMap (); }
            
    public void addClinParam (AttVal nAttVal) {
        
        if (this.clinParamsMap.containsKey(nAttVal.getName())) {
            System.err.println ("Warning: Overwriting existing clinical param: "
                                + nAttVal.getName ());
        }
        this.clinParamsMap.put (nAttVal.getName(), nAttVal);
    }
    
    public AttVal getClinParam (String clinParamName) {
        
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
        }
                
        return temp;
    }  
}

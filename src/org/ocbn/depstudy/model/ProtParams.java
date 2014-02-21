package org.ocbn.depstudy.model;

import java.util.TreeMap;
import org.ocbn.depstudy.util.GenUtil;

/**
 * Brings together a patient, an encounter, a sample, and a set of proteomics 
 * values. 
 * 
 * @author ocbn
 */

public class ProtParams extends Params {
   
    private Sample sample; 
    private Protein protein; 
    private String pepHt;
    private String pepArea;
    private String pepSToN; 
    private final TreeMap <String, AttValLabel> clinParamsMap;
    
    public ProtParams () { this.clinParamsMap = new TreeMap (); }
            
    public void setSample (Sample nSample) {
    
        GenUtil.validateNotNull(nSample);
        this.sample= nSample;
    }
    
    public void setProtein (Protein nProtein) {
        
        GenUtil.validateNotNull (nProtein);
        this.protein = nProtein;
    }
    
    public void setPepHt (String attVal) {
        
        GenUtil.validateNotNull (attVal);
        this.pepHt = attVal;
    }
        
    public void setPepArea (String attVal) {
        
        GenUtil.validateNotNull (attVal);
        this.pepArea = attVal;
    }
    
    public void setPepSToN (String attVal) {
        
        GenUtil.validateNotNull (attVal);
        this.pepSToN = attVal;
    }
            
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
    
    public Protein getProtein () { return this.protein; }
    
    public Sample getSample () { return this.sample; }
    
    public String getPepHt () { return this.pepHt; }

    public String getPepArea() { return this.pepArea; }
   
    public String getStoN () { return this.pepSToN; }
    
    //generic dump, spreadsheet format.
    @Override
    public String toString () {
        
        String temp = ""; 
        for (String clinParamName : this.clinParamsMap.keySet ()) {
            temp += this.getPatient ().getID () + 
                    this.getEncounter ().getType() +
                    clinParamName +
                    clinParamsMap.get (clinParamName).getVal () + 
                    clinParamsMap.get (clinParamName).getValLabel ();
        }
                
        return temp;
    }    
}

package org.ocbn.depstudy.model;

import org.ocbn.depstudy.util.GenUtil;

/**
 * Brings together a patient, an encounter, a sample, and a set of proteomics 
 * values. 
 * The protein params (Ht, Area, SToN) are separated into their own atts 
 * (instead of the generic AttVal)to ease potential post-processing. 
 * 
 * @author ocbn
 */

public class ProtParams extends Params {
   
    private Sample sample; 
    private Protein protein; 
    private String pepHt;
    private String pepArea;
    private String pepSToN; 
   
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
            
    public Protein getProtein () { return this.protein; }
    
    public Sample getSample () { return this.sample; }
    
    public String getPepHt () { return this.pepHt; }

    public String getPepArea() { return this.pepArea; }
   
    public String getStoN () { return this.pepSToN; }
       
    @Override
    public String toStringHeader () {
        
        String temp = "";
        temp += "ID" + GenUtil.TAB + 
                "PatientID" + GenUtil.TAB +
                "EncounterID" + GenUtil.TAB + 
                "SampleID" + GenUtil.TAB + 
                "ProteinID" + GenUtil.TAB +
                "PeptideHt" + GenUtil.TAB +
                "PeptideArea" + GenUtil.TAB +
                "PeptideSToN" + GenUtil.TAB;
       
        return temp;
    }
    
    @Override
    public String toString () {
        
        String temp = ""; 
        temp += this.getDBID() + GenUtil.TAB + 
                this.getPatient ().getDBID () + GenUtil.TAB + 
                this.getEncounter ().getDBID () + GenUtil.TAB + 
                this.getSample ().getDBID () + GenUtil.TAB + 
                this.getProtein ().getDBID () + GenUtil.TAB + 
                this.getPepHt() + GenUtil.TAB + 
                this.getPepArea() + GenUtil.TAB + 
                this.getStoN(); 
        
        return temp;
    }
}

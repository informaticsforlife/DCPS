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
    
    //generic dump, spreadsheet format.
    @Override
    public String toString () {
        
        String temp = ""; 

                
        return temp;
    }    
}

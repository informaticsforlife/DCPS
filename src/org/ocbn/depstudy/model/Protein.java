package org.ocbn.depstudy.model;

/**
 * Seed for a protein/peptide class. 
 * 
 * @author ocbn
 */

public class Protein extends Persistance {

    private String name; 
    private String pepName;
    
    public Protein () { setDBID (Protein.SEQ++); } 
    
    public void setName (String nName) {
        
        this.name = nName;
    }
    
    public void setPepName (String nPepName) {
        
        this.pepName = nPepName;
    }
    
    public String getName () { return name; }
    
    public String getPepName () { return pepName; }
}

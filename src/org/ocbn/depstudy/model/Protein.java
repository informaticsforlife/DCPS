package org.ocbn.depstudy.model;

import org.ocbn.depstudy.util.GenUtil;

/**
 * Seed for a protein/peptide class. 
 * 
 * @author ocbn
 */

public class Protein extends Persistence {

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
    
    public String toStringHeaders () {
       
        String temp = "";
        temp += "ID" + GenUtil.TAB + 
                "Name" + GenUtil.TAB + 
                "PeptideName" + GenUtil.TAB;
        
        return temp;
    }
    
    @Override
    public String toString () {
        
        String temp = "";
        temp += this.getDBID () + GenUtil.TAB + 
                this.getName() + GenUtil.TAB + 
                this.getPepName() + GenUtil.TAB; 
        
        return temp;
    }
}

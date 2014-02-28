package org.ocbn.depstudy.model;

import org.ocbn.depstudy.util.GenUtil;

/**
 * Seed for a protein/peptide class. Each peptide is treated as a separate 
 * protein. For now, no relation is defined between a protein & a peptide, e.g. 
 * parent/child, container-design pattern, ...etc. 
 * 
 * @author ocbn
 */

public class Protein extends Persistence {

    private static int SEQ = 1;
    private String name; 
    private String pepName;
    
    public Protein () { setDBID (Protein.SEQ++); } 
    
    public void setName (String nName) {
        
        GenUtil.validateString(nName);
        this.name = nName;
    }
    
    public void setPepName (String nPepName) {
        
        GenUtil.validateString(nPepName);
        this.pepName = nPepName;
    }
    
    public String getName () { return name; }
    
    public String getPepName () { return pepName; }
    
    public static String toStringHeader () {
       
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

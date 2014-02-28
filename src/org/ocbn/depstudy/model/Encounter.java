package org.ocbn.depstudy.model;

import org.ocbn.depstudy.util.GenUtil;

/**
 * Seed class for an encounter (e.g. visit). 
 * 
 * @author ocbn
 */

public class Encounter extends Persistence {
    
    private static int SEQ = 1;
    private String type;
    
    public Encounter () { setDBID (Encounter.SEQ++); }
    
    public void setType (String nType) {
        
        if (!ModelCV.isValidEncounterType (nType)) {
            throw new IllegalArgumentException ("Invalid Encounter Type: " +
                                                nType);
        }
        this.type = nType;
    }
    
    public String getType () { return this.type; }
    
    public static String toStringHeaders () {
        
        String temp = "";
        
        temp += "ID" + GenUtil.TAB + 
                "Type" + GenUtil.TAB;
        
        return temp;
    }
    @Override
    public String toString () {
        
        String temp = "";
        temp += this.getDBID () + GenUtil.TAB + 
                this.getType();
        
        return temp;
    }
}

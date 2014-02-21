package org.ocbn.depstudy.model;

/**
 * Seed class for an encounter (e.g. visit). 
 * 
 * @author ocbn
 */

public class Encounter {

    private String type;
    
    public void setType (String nType) {
        
        if (!ModelCV.isValidEncounterType (nType)) {
            throw new IllegalArgumentException ("Invalid Encounter Type: " +
                                                nType);
        }
        this.type = nType;
    }
    
    public String getType () { return this.type; }
}

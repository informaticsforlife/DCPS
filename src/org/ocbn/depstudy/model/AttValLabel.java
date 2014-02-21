package org.ocbn.depstudy.model;

/**
 * Extended Att-Val representation, inclusive of a label. 
 * 
 * @author ocbn
 */

public class AttValLabel extends AttVal {

    private String valLabel;
    
    public void setValLabel (String nValLabel) {
        
        this.valLabel = nValLabel;
    }
    
    public String getValLabel () { return this.valLabel; }
}

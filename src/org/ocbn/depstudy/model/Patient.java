package org.ocbn.depstudy.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Seed for a Patient/clinical trials participant class.
 * 
 * @author ocbn
 */

public class Patient {

    private static int INDEX = 1; 
    private String ID; 
    private String secondaryID; 
    private PatientDemo pd; 
    
    //run once
    public static void buildID (Patient p) {

        if (p.getID () != null) {
            throw new IllegalStateException ("Patient already has a unique "
                                             + "study ID: " + p.getID ());
        }
        NumberFormat formatter = new DecimalFormat("0000");
        p.setID (formatter.format(Patient.INDEX++));
    }
    
    public void setID (String nID) {
        
        this.ID = nID;
    }
    
    public void setSecondaryID (String nSecondaryID) {
        
        this.secondaryID = nSecondaryID; 
    }
    
    public void setPatientDemo (PatientDemo npd) {
        
        this.pd = npd;
    }
    
    public String getID () { return this.ID; }
    
    public String getSecondaryID () { return this.secondaryID; }
    
    public PatientDemo getPatientDemo () { return pd; }
}

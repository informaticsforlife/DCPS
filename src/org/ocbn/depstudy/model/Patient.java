package org.ocbn.depstudy.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import org.ocbn.depstudy.util.GenUtil;

/**
 * Seed for a Patient/clinical trials participant class.
 * 
 * @author ocbn
 */

public class Patient extends Persistence {

    private static int SEQ = 1;
    private String ID; 
    private String secondaryID; 
    private PatientDemo pd; 
    
    public Patient () { 
        
        setDBID (Patient.SEQ);
        Patient.SEQ++;
    }
    
    public void setPatientID (int suffix) {
        
        GenUtil.validateNonNegativeInt(suffix);
        NumberFormat formatter = new DecimalFormat("0000");
        setID ( ModelCV.BCID_PREFIX + formatter.format(suffix));         
    }
    
    public void setID (String nID) {
        
        GenUtil.validateString(nID);
        this.ID = nID;
    }
    
    public void setSecondaryID (String nSecondaryID) {
        
        GenUtil.validateString(nSecondaryID);
        this.secondaryID = nSecondaryID; 
    }
    
    public void setPatientDemo (PatientDemo npd) {
    
        GenUtil.validateNotNull(npd);
        this.pd = npd;
    }
    
    public String getID () { return this.ID; }
    
    public String getSecondaryID () { return this.secondaryID; }
    
    public PatientDemo getPatientDemo () { return pd; }
    
    public static String toStringHeader () {
        
        String temp = "";
        temp += "ID" + GenUtil.TAB +
                "PatientID" + GenUtil.TAB +
                "SecondaryID" + GenUtil.TAB +
                PatientDemo.toStringHeader ();
                
        return temp;
    }
    
    @Override
    public String toString () {
        
        String temp = "";
        temp += this.getDBID () + GenUtil.TAB + 
                this.getID() + GenUtil.TAB + 
                this.getSecondaryID() + GenUtil.TAB + 
                this.getPatientDemo();
        
        return temp;
    }

    @Override
    public boolean equals(Object o) {
        
        Patient p2 = (Patient)o;
        
        return getID().equals (p2.getID());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }
}

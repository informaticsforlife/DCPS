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

    private String ID; 
    private String secondaryID; 
    private PatientDemo pd; 
    
    public Patient () { 
        
        setDBID (Patient.SEQ++);
        setPatientID ();
    }
    
    private void setPatientID () {
        
        NumberFormat formatter = new DecimalFormat("0000");
        setID (formatter.format(Patient.SEQ));         
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
    
    public String toStringHeaders () {
        
        String temp = "";
        temp += "ID" + GenUtil.TAB +
                "PatientID" + GenUtil.TAB +
                "SecondaryID" + GenUtil.TAB +
                this.getPatientDemo().toStringHeaders ();
                
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
}

package org.ocbn.depstudy.model;

/**
 * The patient's demographics part. Another definition for it would the data
 * elements that are not likely to change over the course of the study, i.e. 
 * do not need to be captured over several events. For age, its just an 
 * assumption. It is also assumed that the clinical group designation will 
 * not change. 
 * 
 * @author ocbn
 */

public class PatientDemo {

    private int age; 
    private AttValLabel gender; 
    private AttValLabel ethnic;
    private AttVal clinGroup; 
    private String clinGroupHAMD; 
    
    public void setAge (int nAge) {
        
        this.age = nAge; 
    }
    
    public void setGender (AttValLabel nGender) {
        
        this.gender = nGender;
    }
    
    public void setEthnic (AttValLabel nEthnic) {
        
        this.ethnic = nEthnic;
    }
    
    public void setClinGroup (AttVal nClinGroup) {
        
        this.clinGroup = nClinGroup;
    }
    
    public void setClinGroupHAMD (String nClinGroupHAMD) {
        
        this.clinGroupHAMD = nClinGroupHAMD;
    }
    
    public int getAge () { return this.age; }
        
    public AttValLabel getGender () { return this.gender; }
    
    public AttValLabel getEthnic () { return this.ethnic; }
    
    public AttVal getClinGroup () { return this.clinGroup; }
    
    public String getClinGroupHAMD () { return this.clinGroupHAMD; }
}

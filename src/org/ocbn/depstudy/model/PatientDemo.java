package org.ocbn.depstudy.model;

import org.ocbn.depstudy.util.GenUtil;

/**
 * The patient's demographics part. Another definition for it would the data
 * elements that are not likely to change over the course of the study, i.e. 
 * do not need to be captured over several events. For age, its just an 
 * assumption. It is also assumed that the clinical group designation will 
 * not change. 
 * There is redundancy in the case of the attributes saved as AttValLabels
 * in that the attribute name is captured both in the attName as well in the 
 * original attribute name (i.e. gender, ...). This is tolerated for now. 
 * 
 * @author ocbn
 */

public class PatientDemo {

    private int age; 
    private AttValLabel gender; 
    private AttValLabel ethnic;
    private String clinGroup; 
    private String clinGroupHAMD; 
    
    public void setAge (int nAge) {
        
        GenUtil.validateNonNegativeInt(age);
        this.age = nAge; 
    }
    
    public void setGender (AttValLabel nGender) {
        
        GenUtil.validateNotNull(nGender);
        this.gender = nGender;
    }
    
    public void setEthnic (AttValLabel nEthnic) {
        
        GenUtil.validateNotNull(nEthnic);
        this.ethnic = nEthnic;
    }
    
    public void setClinGroup (String nClinGroup) {
        
        GenUtil.validateString (nClinGroup);
        this.clinGroup = nClinGroup;
    }
    
    public void setClinGroupHAMD (String nClinGroupHAMD) {
        
        GenUtil.validateString(nClinGroupHAMD);
        this.clinGroupHAMD = nClinGroupHAMD;
    }
    
    public int getAge () { return this.age; }
        
    public AttValLabel getGender () { return this.gender; }
    
    public AttValLabel getEthnic () { return this.ethnic; }
    
    public String getClinGroup () { return this.clinGroup; }
    
    public String getClinGroupHAMD () { return this.clinGroupHAMD; }
    
    public String toStringHeaders () {
        
        String temp = "";
        temp += "Age" + GenUtil.TAB +
                "ClinicalGroup" + GenUtil.TAB +
                "ClinicalGroupHAMD" + GenUtil.TAB +
                "Gender" + GenUtil.TAB +
                "GenderLabel" + GenUtil.TAB +
                "Ethnicity" + GenUtil.TAB +
                "EthnicityLabel";
               
        return temp;
    }
    
    @Override
    public String toString () {
        
        String temp = "";
        temp += this.getAge() + GenUtil.TAB +
                this.getClinGroup () + GenUtil.TAB +
                this.getClinGroupHAMD() + GenUtil.TAB +
                this.getGender().toString() + GenUtil.TAB +
                this.getEthnic().toString();
        
        return temp;
    }
}

package org.ocbn.depstudy.model;

import org.ocbn.depstudy.util.GenUtil;

/**
 * The patient's demographics part. Another definition for it would the data
 * elements that are not likely to change over the course of the study, i.e. 
 * do not need to be captured over several events. 
 * For age, its just an assumption. It is also assumed that the clinical group 
 * designations will not change. Otherwise, these should be saved under 
 * Clinical Params.
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
    private AttValLabel clinGroup;
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
    
    public void setClinGroup (AttValLabel nClinGroup) {
        
        GenUtil.validateNotNull (nClinGroup);
        this.clinGroup = nClinGroup;
    }
    
    public void setClinGroupHAMD (String nClinGroupHAMD) {
        
        GenUtil.validateString(nClinGroupHAMD);
        this.clinGroupHAMD = nClinGroupHAMD;
    }
    
    public int getAge () { return this.age; }
        
    public AttValLabel getGender () { return this.gender; }
    
    public AttValLabel getEthnic () { return this.ethnic; }
    
    public AttValLabel getClinGroup () { return this.clinGroup; }
    
    public String getClinGroupHAMD () { return this.clinGroupHAMD; }
    
    public static String toStringHeader () {
        
        String temp = "";
        temp += "Age" + GenUtil.TAB +
                "ClinicalGroup" + GenUtil.TAB +
                "ClinicalGroupLabel" + GenUtil.TAB +
                "ClinicalGroupHAMD" + GenUtil.TAB +
                "Gender" + GenUtil.TAB +
                "Gender_Label" + GenUtil.TAB +
                "Ethnicity" + GenUtil.TAB +
                "Ethnicity_Label";
               
        return temp;
    }
    
    @Override
    public String toString () {
        
        String temp = "";
        temp += this.getAge() + GenUtil.TAB + 
                this.getClinGroup ().getVal() + GenUtil.TAB +
                this.getClinGroup().getValLabel() + GenUtil.TAB +
                this.getClinGroupHAMD() + GenUtil.TAB +
                this.getGender().toString() + GenUtil.TAB +
                this.getEthnic().toString();
        
        return temp;
    }
}

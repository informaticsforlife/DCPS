package org.ocbn.depstudy.model;

/**
 * Abstract representation. 
 * 
 * @author ocbn
 */

public abstract class Params {
    
    private Patient patient; 
    private Encounter encounter; 
    
    public void setPatient (Patient nPatient) {
        
        this.patient = nPatient; 
    } 
    
    public void setEncounter (Encounter nEncounter) {
        
        this.encounter = nEncounter;
    }
    
    public Patient getPatient () { return this.patient; }
    
    public Encounter getEncounter () { return this.encounter; }
}

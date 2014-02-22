package org.ocbn.depstudy.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * Container/Manager class for the whole study and the loading process. 
 * - read all entries. 
 * - pull out samples and patients and creeate patient ids after reading 
 * all
 * - master spreadsheet generator, data dumper. 
 * - matching primary keys random, instead of the 
 * atts. 
 * total of 8 or so tables?
 * create headers separately. 
 * 
 * @author ocbn
 */

public class ModelContainer {
   
    private static TreeMap <String, Patient> patientMap; 
    private static TreeMap <String, Encounter> encounterMap;
    private static ArrayList <Params> clinList;
    private static TreeMap <String, Protein> proteinMap;
    private static TreeMap <String, ProtParams> protParamMap;
    
    static {
        
        ModelContainer.patientMap = new TreeMap <> ();
        ModelContainer.encounterMap = new TreeMap <> ();
        ModelContainer.clinList = new ArrayList <> (); 
        
        ModelContainer.proteinMap = new TreeMap <> ();
        ModelContainer.protParamMap = new TreeMap <> ();
    } 
    
    public static Protein getProtein (String proteinName) {
        
        if (proteinMap.containsKey (proteinName)) {
            return proteinMap.get (proteinName);
        }
        Protein p = new Protein ();
        p.setName (proteinName);
        proteinMap.put(proteinName, p);
        
        return p;
    }
    
    public static ProtParams getProtParam (String sampleID) {
    
        if (protParamMap.containsKey (sampleID)) {
            return protParamMap.get (sampleID);
        }
        ProtParams pp = new ProtParams ();
        Sample s = new Sample ();
        s.setName(sampleID);
        pp.setSample (s);
        
        protParamMap.put (sampleID, pp);
        
        return pp;
    } 
    
    public static TreeMap <String, ProtParams> getProtParams () { 
        
        return ModelContainer.protParamMap; 
    } 
    
    //assumes no protein existed without params
    public static TreeMap <String, Protein> getProteins () {
        
        Iterator iterator = protParamMap.keySet().iterator();
        TreeMap <String, Protein> proteinMap = new TreeMap <> ();
        while (iterator.hasNext ()) {
            ProtParams pp = protParamMap.get ((String)iterator.next ());
            if (!proteinMap.containsKey (pp.getProtein().getName())) {
                proteinMap.put (pp.getProtein().getName(), pp.getProtein());
            }
        }
        
        return proteinMap;
    }
    
    public static TreeMap <String, Sample> getSamples () {
        
        Iterator iterator = protParamMap.keySet().iterator();
        TreeMap <String, Sample> sampleMap = new TreeMap <> ();
        while (iterator.hasNext ()) {
            ProtParams pp = protParamMap.get ((String)iterator.next ());
            if (sampleMap.containsKey (pp.getSample().getID ())) {
                throw new IllegalStateException ("Redundant samples in protein"
                + " params.");
            }
            sampleMap.put (pp.getSample().getID(), pp.getSample ());           
        }
        
        return sampleMap;
    }
        
    private void dumpAll (String patientFileName, String encounterFileName, 
                          String clinParamsFileName, String protParamsFileName) {
        
        int pKey = 1, eKey = 1, clinKey = 1, protKey = 1;
        
        for (int i = 0; i < this.clinList.size (); i++) {
            Params cParams = clinList.get (i);
            System.out.println (pKey++ + cParams.toString());
        }
    }
}

package org.ocbn.depstudy.model;

import au.com.bytecode.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlled vocabularies - codes for the Study plus all the clinical variables
 * monitored. Dispenses the appropriate label per code requested. 
 * 
 * @author ocbn
 */

public class ModelCV {
    
    private static ArrayList <AttValLabel> elementList; 
    //Used by encounter class - type attribute. Maps to 0, 2, 3 codes. 
    public static final String ENCOUNTER_BASELINE = "Baseline";
    public static final String ENCOUNTER_2 = "Visit 2";
    public static final String ENCOUNTER_3 = "Visit 3";
    //BrainCODE study-specific ID prefix.
    public static final String BCID_PREFIX = "CBN04_TGH_";
    //depression level
    public static final String NDEP = "nondepressed";
    public static final String DEP = "depressed";
    public static final String IDEP = "intermediate";

    public static void initializeLabelsFile (String elementFileName) {
        
        ModelCV.elementList = new ArrayList ();
        try {
            CSVReader reader = new CSVReader(new FileReader (elementFileName));
            List eList = reader.readAll();
            ModelCV.elementList.addAll(eList);
        } catch (IOException e) {
            throw new RuntimeException ("Unable to read Element Labels file."
                                        + elementFileName);
        }
    }
    
    public static final boolean isValidEncounterType (String type) {
        
        return ModelCV.ENCOUNTER_BASELINE.equals (type) ||
               ModelCV.ENCOUNTER_2.equals (type) ||
               ModelCV.ENCOUNTER_3.equals (type);
    }
    
    public static final boolean isValidDepType (String type) {
        
        return ModelCV.NDEP.equals (type) ||
               ModelCV.DEP.equals (type) ||
               ModelCV.IDEP.equals (type);    
    }

    //clinical params monitored at each encounter    
    public static final String BMI = "BMI";	
    public static final String SES = "SES"; 	
    public static final String AU = "Antidepressant.use";	
    public static final String CS = "Cancer.Stage";	
    public static final String CT = "Cancer.Type";	
    public static final String AT = "Active.Treatment";	
    public static final String GROUP = "Group";	
    public static final String SD = "SCID.depression";	
    public static final String TD = "Total.Depression";	
    public static final String HA = "Ham.Anxiety";	
    public static final String HD = "Ham.Depression";	
    public static final String HI = "Ham.Insomnia";	
    public static final String HS = "Ham.Somatic";	
    public static final String ANX = "Anxiety";	
    public static final String HW = "Health.worries";	
    public static final String SADD = "Sad.Dep";	
    public static final String REST = "Restlessness";	
    public static final String NIIP = "No.interest.in.ppl";	
    public static final String NIIA = "No.interest.in.act";	
    public static final String DMD = "Diff.making.decisions";	
    public static final String ST = "Strange.thoughts";	
    public static final String AW = "Allover.sick";	
    public static final String DGTS = "Dif.getting.to.sleep";	
    public static final String DSA = "Dif.staying.alseep";	
    public static final String STM = "Sleep.too.much";	
    public static final String NAUSE = "Nausea";	
    public static final String VOMIT = "Vomiting";	
    public static final String LA = "Loss.appetite";	
    public static final String FATIG = "Fatigue";	
    public static final String DISTR = "Distractibility";	
    public static final String BA = "Body.aches";	
    public static final String JP = "Joint.Pain";	
    public static final String OP = "Other.Pain";	
    public static final String EOC = "Episodes.of.confusion";	
    public static final String WP = "Wordfinding.probs";	
    public static final String MP = "Memory.probs";	
    public static final String IRRAT = "Irratibility";	
    public static final String DM = "Decreased.Motivation";	
    public static final String HALLU = "Hallucinations";	
    public static final String LOE = "Lack.of.emotion";	
    public static final String MS = "Mood.swings";	
    public static final String TENSI = "Tension";	
    public static final String SM = "Slowed.movements";	
    public static final String TS = "Tremor.shakiness";	
    public static final String WALKP = "Walking.probs";	
    public static final String VP = "Vision.probs";	
    public static final String BBP = "Bowel.bladder.probs";	
    public static final String LOIIS = "Loss.of.interest.in.sex";	
    public static final String FEVER = "Fever";	
    public static final String HEADA = "Headaches";	
    public static final String TN = "Total.NRS";
    public static final String NI = "NRS.insomnia";
    public static final String NP = "NRS.pain";
    public static final String NC = "NRS.cognitive";

    /*
    public static final String GENDER_MALE = "male";
    public static final String GENDER_FEMALE = "female";
    public static final String GROUP_1 = "Cancer Patient";
    public static final String GROUP_2 = "Caregiver";
    public static final String GROUP_3 = "Control";    
    public static final String SCID_DEP_0 = "Not Depressed";
    public static final String SCID_DEP_1 = "Depressed";
    public static final String STATUS_DEPRESSED = "depressed";
    public static final String DEPGROUP_1 = "Depressed - Patient";
    public static final String DEPGROUP_2 = "Not Depressed - Patient";
    public static final String DEPGROUP_3 = "Depressed - Caregiver";
    public static final String DEPGROUP_4 = "Not Depressed - Caregiver";
    public static final String DEPGROUP_5 = "Not Depressed - Control";
    public static final String ETHNIC_1 = "Caucasian";  
    public static final String ETHNIC_2 = "Asian";  
    public static final String ETHNIC_3 = "Other";  
    public static final String ANTIDEP_USE_1 = "Yes";
    public static final String ANTIDEP_USE_2 = "No";
    public static final String CANCER_STAGE_1 = "Early 1/2";
    public static final String CANCER_STAGE_2 = "Late 3/4";
    public static final String CANCER_STAGE_3 = "Hematologic";
    public static final String ACTIVE_TREAT_0 = "None";
    public static final String ACTIVE_TREAT_1 = "Chemo Or Radiation";
    public static final String CANCER_TYPE_1 = "Lung";
    public static final String CANCER_TYPE_2 = 
              "GI (gastric, liver, colorectal, gallbladder, pancreatic)";
    public static final String CANCER_TYPE_3 = "Gyne";
    public static final String CANCER_TYPE_4 = "Breast";
    public static final String CANCER_TYPE_5 = "Haematologic";
    */	
}

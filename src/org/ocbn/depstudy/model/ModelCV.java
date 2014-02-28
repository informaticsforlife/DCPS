package org.ocbn.depstudy.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.ocbn.depstudy.util.GenUtil;

/**
 * Controlled vocabularies for all the variables. 
 * Dispenses the appropriate label per code requested. 
 * Could optionally move the clinical and lab param names into a prop file. 
 * 
 * @author ocbn
 */

public class ModelCV {
    
    private static ArrayList <AttValLabel> elementList = new ArrayList ();
    private static ArrayList <String> clinParamList = new ArrayList ();
    private static ArrayList <String> labParamList = new ArrayList ();
    
    //Used by encounter class - type attribute. Maps to 0, 2, 3 codes. 
    public static final String ENCOUNTER_BASELINE = "1"; //"Baseline";
    public static final String ENCOUNTER_2 = "2"; //"Visit 2";
    public static final String ENCOUNTER_3 = "3"; //"Visit 3";
    //BrainCODE study-specific ID prefix.
    protected static final String BCID_PREFIX = "CBN04_TGH_";
    public static final String CT_OTHER = "9996";

    //Column headers CV, needed for parsing
    //patient
    public static final String PID = "Patient.ID";
    public static final String DEPRESSED = "Depressed";
    public static final String COL_CGH = "Clinical.Group.HAMD.Below.14";
    public static final String CG = "Clinical.Group";
    public static final String AGE = "Age";
    public static final String GEN = "Gender";
    public static final String ETH = "Ethnicity";
    
    //encounter
    public static final String BASE = "Baseline";
 
    //sample
    public static final String SN = "SampleName";
    public static final String DO = "Dataset.Order";
    public static final String BLOCK = "Block";
    public static final String BO = "Block.Order";
    public static final String SI = "Sample.ID";

    //clinical params 
    protected static final String BMI = "BMI";	
    protected static final String SES = "SES"; 	
    protected static final String AU = "Antidepressant.use";	
    protected static final String CS = "Cancer.Stage";	
    public static final String CT = "Cancer.Type";          //exception
    protected static final String AT = "Active.Treatment";	
    protected static final String GROUP = "Group";	
    protected static final String SD = "SCID.depression";	
    protected static final String TD = "Total.Depression";	
    protected static final String HA = "Ham.Anxiety";	
    protected static final String HD = "Ham.Depression";	
    protected static final String HI = "Ham.Insomnia";	
    protected static final String HS = "Ham.Somatic";	
    protected static final String ANX = "Anxiety";	
    protected static final String HW = "Health.worries";	
    protected static final String SADD = "Sad.Dep";	
    protected static final String REST = "Restlessness";	
    protected static final String NIIP = "No.interest.in.ppl";	
    protected static final String NIIA = "No.interest.in.act";	
    protected static final String DMD = "Diff.making.decisions";	
    protected static final String ST = "Strange.thoughts";	
    protected static final String AW = "Allover.sick";	
    protected static final String DGTS = "Dif.getting.to.sleep";	
    protected static final String DSA = "Dif.staying.alseep";	
    protected static final String STM = "Sleep.too.much";	
    protected static final String NAUSE = "Nausea";	
    protected static final String VOMIT = "Vomiting";	
    protected static final String LA = "Loss.appetite";	
    protected static final String FATIG = "Fatigue";	
    protected static final String DISTR = "Distractibility";	
    protected static final String BA = "Body.aches";	
    protected static final String JP = "Joint.Pain";	
    protected static final String OP = "Other.Pain";	
    protected static final String EOC = "Episodes.of.confusion";	
    protected static final String WP = "Wordfinding.probs";	
    protected static final String MP = "Memory.probs";	
    protected static final String IRRAT = "Irratibility";	
    protected static final String DM = "Decreased.Motivation";	
    protected static final String HALLU = "Hallucinations";	
    protected static final String LOE = "Lack.of.emotion";	
    protected static final String MS = "Mood.swings";	
    protected static final String TENSI = "Tension";	
    protected static final String SM = "Slowed.movements";	
    protected static final String TS = "Tremor.shakiness";	
    protected static final String WALKP = "Walking.probs";	
    protected static final String VP = "Vision.probs";	
    protected static final String BBP = "Bowel.bladder.probs";	
    protected static final String LOIIS = "Loss.of.interest.in.sex";	
    protected static final String FEVER = "Fever";	
    protected static final String HEADA = "Headaches";	
    protected static final String TN = "Total.NRS";
    protected static final String NI = "NRS.insomnia";
    protected static final String NP = "NRS.pain";
    protected static final String NC = "NRS.cognitive";
    protected static final String DEPG = "Depression.Group";
    protected static final String DEPM = "Depressed.Mood";
    protected static final String FOG = "Feelings.of.Guilt";
    protected static final String SUI = "Suicide";
    protected static final String INSE = "Insomnia.Early";
    protected static final String INSM = "Insomnia.Mild";
    protected static final String INSL = "Insomnia.Late";
    protected static final String WAA = "Work.and.Activities";
    protected static final String RET = "Retardation";
    protected static final String AGI = "Agitation";
    protected static final String ANXP = "Anxiety.Psychic";
    protected static final String ANXS = "Anxiety.Somatic";

    protected static final String SSGA = "Somatic.Symptoms.Gastrointestinal";
    protected static final String SSGE = "Somatic.Symptoms.General";
    protected static final String GS = "Genital.Symptoms";
    protected static final String HYPO = "Hypochondriasis";
    protected static final String LOW = "Loss.of.Weight.a";
    protected static final String INS = "Insight";
    
    //lab params (not following naming convention)
    protected static final String IL6 = "IL6";	
    protected static final String IFNg = "IFNg";
    protected static final String IL10 = "IL10";
    protected static final String IL12p70 = "IL12p70";
    protected static final String IL13 = "IL13";
    protected static final String IL1b = "IL1b";
    protected static final String IL2 = "IL2";	
    protected static final String IL4 = "IL4";
    protected static final String IL5 = "IL5";
    protected static final String IL8 = "IL8";
    protected static final String TNFa = "TNFa";
    protected static final String IL1Ra = "IL1Ra";	
    protected static final String IL17 = "IL17";
    protected static final String IL12p40 = "IL12p40";	
    protected static final String IL2ra1 = "IL2ra1";
    protected static final String Eotaxin = "Eotaxin";	
    protected static final String IP10 = "IP10";
    protected static final String MCP1a = "MCP1a";

    //output file names.
    public static final String P_TABLE = "./Output/PatientsTable.txt";
    public static final String ENC_TABLE = "./Output/EncountersTable.txt";
    public static final String PP_TABLE = "./Output/ProteinsTable.txt";
    public static final String SAMPLE_TABLE = "./Output/SamplesTable.txt";
    public static final String CP_TABLE = "./Output/ClinParamsTable.txt";
    public static final String LP_TABLE = "./Output/LabParamsTable.txt";
    public static final String ProtP_TABLE = "./Output/ProteinParamsTable.txt";
            
    //Proteomics related variables tracked.
    public static final String MT_AREA = "area";
    public static final String MT_SN = "sn";
    public static final String MT_HT = "height";
    
    static {  
        ModelCV.clinParamList.add (BMI);	
        ModelCV.clinParamList.add (SES);	
        ModelCV.clinParamList.add (AU);	
        ModelCV.clinParamList.add (CS);	
        ModelCV.clinParamList.add (CT);	
        ModelCV.clinParamList.add (AT);
        ModelCV.clinParamList.add (GROUP);
        ModelCV.clinParamList.add (SD);	
        ModelCV.clinParamList.add (TD);
        ModelCV.clinParamList.add (HA);	
        ModelCV.clinParamList.add (HD);	
        ModelCV.clinParamList.add (HI);	
        ModelCV.clinParamList.add (HS);	
        ModelCV.clinParamList.add (ANX);
        ModelCV.clinParamList.add (HW);
        ModelCV.clinParamList.add (SADD);	
        ModelCV.clinParamList.add (REST);	
        ModelCV.clinParamList.add (NIIP);	
        ModelCV.clinParamList.add (NIIA);	
        ModelCV.clinParamList.add (DMD);	
        ModelCV.clinParamList.add (ST);
        ModelCV.clinParamList.add (AW);	
        ModelCV.clinParamList.add (DGTS);	
        ModelCV.clinParamList.add (DSA);	
        ModelCV.clinParamList.add (STM);
        ModelCV.clinParamList.add (NAUSE);	
        ModelCV.clinParamList.add (VOMIT);	
        ModelCV.clinParamList.add (LA);	
        ModelCV.clinParamList.add (FATIG);	
        ModelCV.clinParamList.add (DISTR);	
        ModelCV.clinParamList.add (BA);	
        ModelCV.clinParamList.add (JP);	
        ModelCV.clinParamList.add (OP);	
        ModelCV.clinParamList.add (EOC);	
        ModelCV.clinParamList.add (WP);	
        ModelCV.clinParamList.add (MP);	
        ModelCV.clinParamList.add (IRRAT);
        ModelCV.clinParamList.add (DM);	
        ModelCV.clinParamList.add (HALLU);	
        ModelCV.clinParamList.add (LOE);
        ModelCV.clinParamList.add (MS);	
        ModelCV.clinParamList.add (TENSI);	
        ModelCV.clinParamList.add (SM);
        ModelCV.clinParamList.add (TS);	
        ModelCV.clinParamList.add (WALKP);
        ModelCV.clinParamList.add (VP);	
        ModelCV.clinParamList.add (BBP);	
        ModelCV.clinParamList.add (LOIIS);	
        ModelCV.clinParamList.add (FEVER);
        ModelCV.clinParamList.add (HEADA);	
        ModelCV.clinParamList.add (TN);
        ModelCV.clinParamList.add (NI);
        ModelCV.clinParamList.add (NP);
        ModelCV.clinParamList.add (NC);
        ModelCV.clinParamList.add (DEPG);
        ModelCV.clinParamList.add (DEPM);
        ModelCV.clinParamList.add (FOG);
        ModelCV.clinParamList.add (SUI);
        ModelCV.clinParamList.add (INSE);
        ModelCV.clinParamList.add (INSM);
        ModelCV.clinParamList.add (INSL);
        ModelCV.clinParamList.add (WAA);
        ModelCV.clinParamList.add (RET);
        ModelCV.clinParamList.add (AGI);
        ModelCV.clinParamList.add (ANXP);
        ModelCV.clinParamList.add (ANXS);
        ModelCV.clinParamList.add (SSGA);
        ModelCV.clinParamList.add (SSGE);
        ModelCV.clinParamList.add (GS);
        ModelCV.clinParamList.add (HYPO);
        ModelCV.clinParamList.add (LOW);
        ModelCV.clinParamList.add (INS);

        ModelCV.labParamList.add (IL6);	
        ModelCV.labParamList.add (IFNg);
        ModelCV.labParamList.add (IL10);
        ModelCV.labParamList.add (IL12p70);
        ModelCV.labParamList.add (IL13);
        ModelCV.labParamList.add (IL1b);
        ModelCV.labParamList.add (IL2);	
        ModelCV.labParamList.add (IL4);
        ModelCV.labParamList.add (IL5);
        ModelCV.labParamList.add (IL8);
        ModelCV.labParamList.add (TNFa);
        ModelCV.labParamList.add (IL1Ra);	
        ModelCV.labParamList.add (IL17);
        ModelCV.labParamList.add (IL12p40);	
        ModelCV.labParamList.add (IL2ra1);
        ModelCV.labParamList.add (Eotaxin);	
        ModelCV.labParamList.add (IP10);
        ModelCV.labParamList.add (MCP1a);
    }
    
    public static void initializeLabelsFile (String elementFileName) {
        
        try {
            BufferedReader br = new BufferedReader (new FileReader (elementFileName));
            String line;
            AttValLabel avl;
            String [] headersArr = null;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                String [] tempArr = line.split(GenUtil.TAB);
                //skip empty trailing lines
                if (tempArr.length < 3) {                 //i.e. no code/label
                    continue;
                }
                if (headersArr == null) {
                    headersArr = tempArr;
                    continue;
                }
                avl = new AttValLabel();
                avl.setName(tempArr [0]);
                avl.setVal(tempArr [1]);
                avl.setValLabel(tempArr [2]);
                ModelCV.elementList.add (avl);
            }
        } catch (IOException e) {
            throw new RuntimeException ("Unable to read Element Labels file."
                                        + elementFileName);
        }
        //debugging
        /*
        System.out.println ("Listing all code/label pairs: ");
        for (int i = 0; i < ModelCV.elementList.size (); i++) {
            System.out.println (ModelCV.elementList.get (i));
        }
        */
    }
    
    public static final String getMapping (String elementName, String elementCode) {
    
        for (int i = 0; i < ModelCV.elementList.size (); i++) {
            if (ModelCV.elementList.get (i).getName ().equals (elementName) && 
                ModelCV.elementList.get (i).getVal ().equals (elementCode)) {
                return ModelCV.elementList.get(i).getValLabel ();
            }
        }
        
        return null;
    }
    
    public static boolean isValidClinParam (String paramName) {
        
        return ModelCV.clinParamList.contains(paramName);
    }
    
    public static boolean isValidLabParam (String paramName) {
        
        return ModelCV.labParamList.contains(paramName);
    }
 
    protected static final boolean isValidEncounterType (String type) {
        
        return ModelCV.ENCOUNTER_BASELINE.equals (type) ||
               ModelCV.ENCOUNTER_2.equals (type) ||
               ModelCV.ENCOUNTER_3.equals (type);
    }
}

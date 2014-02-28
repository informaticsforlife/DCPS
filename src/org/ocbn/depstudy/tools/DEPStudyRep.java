package org.ocbn.depstudy.tools;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.TreeMap;
import org.ocbn.depstudy.model.Encounter;
import org.ocbn.depstudy.model.ModelCV;
import org.ocbn.depstudy.model.ModelContainer;
import org.ocbn.depstudy.model.Params;
import org.ocbn.depstudy.model.Patient;
import org.ocbn.depstudy.model.ProtParams;
import org.ocbn.depstudy.model.Protein;
import org.ocbn.depstudy.model.Sample;

/**
 * Dumps the tables of the relational database created, in tab delimited 
 * format. 
 * 
 * Could also use setters/getters for access to the 'handy' instance atts. 
 * 
 * @author ocbn
 */

public class DEPStudyRep {
    
    private BufferedWriter bw;
    private TreeMap tempMap;     
    private String tableHeaders;
    
    protected DEPStudyRep (String repMode) {}

    protected void dumpEntries() throws IOException {
     
        this.tempMap = ModelContainer.getPatients();
        System.out.println ("Dumping Patients table: " + this.tempMap.size ());
        this.bw = new BufferedWriter (new FileWriter (ModelCV.P_TABLE));
        this.tableHeaders = Patient.toStringHeader();
        this.dumpOutput();
        
        this.tempMap = ModelContainer.getEncounters();
        System.out.println ("Dumping Encounters table: " + this.tempMap.size ());
        this.bw = new BufferedWriter (new FileWriter (ModelCV.ENC_TABLE));
        this.tableHeaders = Encounter.toStringHeaders();
        this.dumpOutput();
        
        this.tempMap = ModelContainer.getSamples();
        System.out.println ("Dumping Samples table: " + this.tempMap.size ());
        this.bw = new BufferedWriter (new FileWriter (ModelCV.SAMPLE_TABLE));
        this.tableHeaders = Sample.toStringHeader();
        this.dumpOutput();
        
        this.tempMap = ModelContainer.getProteins();
        System.out.println ("Dumping Proteins table: " + this.tempMap.size ());
        this.bw = new BufferedWriter (new FileWriter (ModelCV.PP_TABLE));
        this.tableHeaders = Protein.toStringHeader();
        this.dumpOutput();
        
        this.tempMap = ModelContainer.getClinParams();
        System.out.println ("Dumping 'Clinical' Parameters table: " + this.tempMap.size ());
        this.bw = new BufferedWriter (new FileWriter (ModelCV.CP_TABLE));
        this.tableHeaders = ((Params)this.tempMap.firstEntry().getValue()).toStringHeader();
        this.dumpOutput();
        
        this.tempMap = ModelContainer.getLabParams();
        System.out.println ("Dumping 'Lab' Parameters table: " + this.tempMap.size ());
        this.bw = new BufferedWriter (new FileWriter (ModelCV.LP_TABLE));
        this.tableHeaders = ((Params)this.tempMap.firstEntry().getValue()).toStringHeader();
        this.dumpOutput();
        
        this.tempMap = ModelContainer.getProtParams();
        System.out.println ("Dumping Protein Parameters table: " + this.tempMap.size ());
        this.bw = new BufferedWriter (new FileWriter (ModelCV.ProtP_TABLE));
        this.tableHeaders = ((ProtParams)this.tempMap.firstEntry().getValue()).toStringHeader();
        this.dumpOutput();
    }
    
    //utility method
    private void dumpOutput () throws IOException {

        Iterator iterator = this.tempMap.keySet().iterator();
        //System.out.println (this.tableHeaders);
        this.bw.write (this.tableHeaders);
        this.bw.newLine();
        while (iterator.hasNext()) {
            String key = (String)iterator.next();
            //System.out.println (this.tempMap.get(key));
            this.bw.write(this.tempMap.get (key).toString());
            this.bw.newLine();
        }
        this.bw.flush();
    }    
}

package org.ocbn.depstudy.tools;

import java.io.BufferedReader;
import java.io.PrintWriter;
import org.ocbn.depstudy.util.DefParams;
import org.ocbn.depstudy.util.GenUtil;

/**
 * Handler for reading and writing data in various formats. 
 * 
 * @author elbadr
 */

public class DEPStudyHandler {
    
    //DEP file handler
    protected static final String FILE_TYPE_DEP_STUDY = "DEP_STUDY";

    private static PrintWriter log;
    protected BufferedReader br;
    
    public static void main (String args []) {
        
        log = GenUtil.getDefaultLog ();
        log.println (GenUtil.getTimeStamp ());
        GenUtil.registerStart ();
        String usageMsg = "Usage: DEPStudyHandler DataFilePath RepMode MappingFilePath";
        String warnMsg = "WARNING: Missing command line args. Using defaults from PROP file.";
       
        if (args == null || args.length < 3){
            log.println (warnMsg);
            log.println (usageMsg);
            System.out.println (warnMsg);
            System.out.println (usageMsg);
            args = DefParams.getCommandLineArgs (DEPStudyHandler.class.getName ());
        }
        try {
            DEPStudyReader dsr = new DEPStudyReader (args [0], args [2]);
            dsr.readEntries ();
            DEPStudyRep dsRep = new DEPStudyRep(args [1]);
            dsRep.dumpEntries ();
        } catch (Throwable e) {
            System.err.println ("Unable to process request: DEPStudyHandler.");
            e.printStackTrace (System.out);
            e.printStackTrace(log);
        } finally {
           log.println (GenUtil.getExecTimeStr ());
           log.flush ();
           log.close ();
           System.out.println (GenUtil.getExecTimeStr ());
        }
    }
}
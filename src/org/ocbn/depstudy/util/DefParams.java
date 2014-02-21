package org.ocbn.depstudy.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Connects to all the default names for files and command line args for the
 * different classes.
 * 
 * @author ocbn
 */

public class DefParams {

    //general handles
    public static final String LOG_FILE_PROP = "DefLogFile";
    public static final String DB_FILE_PROP = "DBFile";
    public static final String PROP_FILE_NAME_PROP = "DEP_Props";
    public static final String PROP_DN_SAMPLE_ALL = "DNSampleAll";

    private static final ArrayList <String> defParamsList;
    public static final String PROP_FILE_PATH_BIF = "./Resources/DEP.properties";

    //a command line argument that is set to the default value by the 
    //respective program
    private static Properties props;
    
    static {
        try {
            props = new Properties ();
            String propsFileName = System.getProperty (DefParams.PROP_FILE_NAME_PROP);
            if (propsFileName == null) {
                propsFileName = DefParams.PROP_FILE_PATH_BIF;
            }
            props.load (new FileInputStream (propsFileName));
        } catch (IOException e) {
            e.printStackTrace (System.out);
        }
        defParamsList = new ArrayList <> ();
        defParamsList.add (DefParams.LOG_FILE_PROP);
        defParamsList.add(DefParams.DB_FILE_PROP);
        defParamsList.add (DefParams.PROP_DN_SAMPLE_ALL);
    }

    private DefParams () {}                             //no instances allowed
    
    public static String [] getCommandLineArgs (String className) {
        
        if (className == null || className.length () == 0) {
            throw new IllegalArgumentException ("Invalid argument: " +
                                                 className);
        }
        String [] tempArr = className.split ("\\" + GenUtil.DOT);
        className = tempArr [tempArr.length - 1];
        String argNumStr = props.getProperty (className);
        if (argNumStr == null) {
            throw new RuntimeException ("No args found for class: " +
                                         className);
        }
        int argNum = Integer.parseInt (argNumStr);
        String [] args = new String [argNum];
        for (int i = 0; i < argNum; i++) {
            args [i] = props.getProperty (className + GenUtil.UNDERSCORE + i).trim();
            System.out.println ("args[" + i + "]: " + args[i]);
        }
        
        return args;
    }
        
    public static String getDefaultProp (String propName) {

        if (!defParamsList.contains (propName)) {
            throw new IllegalArgumentException ("Invalid property name: " + 
                propName);
        }
        String propVal = props.getProperty (propName);
        
        return propVal == null ? null : propVal.trim ();
    }
}
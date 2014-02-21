package org.ocbn.depstudy.tools;

import java.util.Iterator;
import java.util.TreeMap;
import org.ocbn.depstudy.model.ModelContainer;
import org.ocbn.depstudy.model.ProtParams;
import org.ocbn.depstudy.model.Protein;
import org.ocbn.depstudy.model.Sample;
import org.ocbn.depstudy.util.GenUtil;

/**
 *
 * @author ocbn
 */

public class DEPStudyRep {
    
    protected DEPStudyRep (String repMode) {}

    protected void dumpEntries() {
    
        int pDBID = 1, ppDBID = 1, sDBID = 1;
        
        //dump proteins
        String pStr = "DBID" + GenUtil.TAB + "Name" + GenUtil.TAB + "Syn" + 
                      GenUtil.NEWLINE;
        TreeMap <String, Protein> pMap = ModelContainer.getProteins ();
        Iterator iterator = pMap.keySet ().iterator ();
        while (iterator.hasNext ()) {
            Protein p = pMap.get ((String) iterator.next ());
            if (p.getPepName()!= null) {
                pStr += pDBID++ + GenUtil.TAB + p.getName() + GenUtil.TAB +
                        p.getPepName() + GenUtil.NEWLINE;
            } else {
                pStr += pDBID++ + GenUtil.TAB + p.getName() + GenUtil.TAB + 
                        GenUtil.NA + GenUtil.NEWLINE; 
            }
        }
        System.out.println(pStr);
        
        //dump samples
        String sStr = "DBID" + GenUtil.TAB + "ID" + GenUtil.TAB + "Name" + 
                      GenUtil.NEWLINE;
        TreeMap <String, Sample> sMap = ModelContainer.getSamples ();
        iterator = sMap.keySet ().iterator ();
        while (iterator.hasNext ()) {
            Sample s = sMap.get ((String) iterator.next ());
            sStr += sDBID++ + GenUtil.TAB + s.getID () + GenUtil.TAB +
                        s.getName () + GenUtil.NEWLINE;
        }
        System.out.println(sStr);
        
        //dump proteins & protein params
        TreeMap <String, ProtParams> ppMap = ModelContainer.getProtParams();
          
    }
    
    
}

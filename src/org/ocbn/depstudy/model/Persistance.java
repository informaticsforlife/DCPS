package org.ocbn.depstudy.model;

import org.ocbn.depstudy.util.GenUtil;

/**
 * Root for all persistable objects in this model. For now, this is handled by
 * creating unique object IDs (i.e. DBIDs), because persistance is delegated
 * to another system. 
 * 
 * @author Rashad
 */

public abstract class Persistance {
    
    protected static int SEQ = 1;
    private int DBID; 
    
    protected void setDBID (int nDBID) {
        
        GenUtil.validateNonNegativeInt(nDBID);
        this.DBID = nDBID;
    }
    
    protected int getDBID () { return this.DBID; } 
    
    protected String getDBIDStr () { return String.valueOf(this.getDBID ()); }
}

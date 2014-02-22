package org.ocbn.depstudy.model;

import org.ocbn.depstudy.util.GenUtil;

/**
 * Root for all persistable objects in this model. For now, this is handled by
 * creating unique object IDs (i.e. DBIDs), because persistence is delegated
 * to another system. 
 * 
 * @author Rashad
 */

public abstract class Persistence {
    
    protected static int SEQ = 1;
    private int DBID; 
    
    protected void setDBID (int nDBID) {
        
        GenUtil.validateNonNegativeInt(nDBID);
        this.DBID = nDBID;
    }
    
    protected int getDBID () { return this.DBID; } 
    
    @Override
    public String toString () { return String.valueOf (getDBID ()); }
}

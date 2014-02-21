package org.ocbn.depstudy.model;

/**
 * Seed for a sample class. 
 * 
 * @author ocbn
 */

public class Sample implements Comparable {

    private String ID; 
    private String name; 
    private int dataSetOrder;
    private int blockOrder; 
    private int blockNum;
    
    public void setID (String nID) {
        
        this.ID = nID; 
    }
    
    public void setName (String nName) {
        
        this.name = nName; 
    }
    
    public void setDataSetOrder (int nDataSetOrder) {
        
        this.dataSetOrder = nDataSetOrder;
    }
    
    public void setBlockOrder (int nBlockOrder) {
        
        this.blockOrder = nBlockOrder;
    }
        
    public void setBlockNum (int nBlockNum) {
        
        this.blockNum = nBlockNum;
    }
    
    public String getID () { return this.ID; }
    
    public String getName () { return this.name; }
    
    public int getDataSetOrder () { return this.dataSetOrder; }
    
    public int getBlockOrder () { return this.blockOrder; }
    
    public int getBlockNum () { return this.blockNum; }
    
    @Override
    public int compareTo (Object o2) {

        Sample s1 = this;
        Sample s2 = (Sample)o2; 
        
        if (s1.getBlockNum() > s2.getBlockNum()) {
            return +1;            
        } else if (s1.getBlockNum() < s2.getBlockNum()) {
            return -1;
        } else {
            if (s1.getBlockOrder() > s2.getBlockOrder()) {
                return +1;
            } else if (s1.getBlockOrder() < s2.getBlockOrder()) {
                return -1;
            } else {                                  //should not happen.
                return 0;
            }
        }
    }
}

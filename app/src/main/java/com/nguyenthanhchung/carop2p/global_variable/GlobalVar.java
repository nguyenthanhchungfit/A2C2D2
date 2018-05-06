package com.nguyenthanhchung.carop2p.global_variable;

public class GlobalVar {
    private static GlobalVar mInstance = null;
    private boolean isSoundOn;
    private GlobalVar(){ }
    public void setSoundStatus(boolean b){
        this.isSoundOn = b;
    }
    public boolean getSoundStatus(){
        return this.isSoundOn;
    }
    public static synchronized GlobalVar getInstance(){
        if(mInstance==null){
            mInstance = new GlobalVar();
        }
        return mInstance;
    }
}

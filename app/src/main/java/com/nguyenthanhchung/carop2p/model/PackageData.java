package com.nguyenthanhchung.carop2p.model;

public class PackageData {
    public TypePackage type;
    public String msg;

    public PackageData(){
        type = TypePackage.ERROR;
        msg = "";
    }

    void parse(String text){
        String[] listItem = text.split("|");
        type = TypePackage.fromString(listItem[0]);
        msg = listItem[1];
    }

    @Override
    public String toString(){
        return type.toString() + "|" + msg;
    }
}

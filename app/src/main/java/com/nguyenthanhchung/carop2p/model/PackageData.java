package com.nguyenthanhchung.carop2p.model;

public class PackageData {
    private TypePackage type;
    private String msg;

    public TypePackage getType() {
        return type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setType(TypePackage type) {
        this.type = type;
    }

    public PackageData(){
        type = TypePackage.ERROR;
        msg = "";
    }

    public PackageData(TypePackage type, String msg){
        this.type = type;
        this.msg = msg;
    }

    public PackageData(String msg){
        this.parse(msg);
    }

    public void parse(String text){
        String[] listItem = text.split(";");
        type = TypePackage.fromString(listItem[0]);
        msg = listItem[1];
    }

    @Override
    public String toString(){
        return type.toString() + ";" + msg;
    }
}

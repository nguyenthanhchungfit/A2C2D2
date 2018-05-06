package com.nguyenthanhchung.carop2p.model;

public enum TypePackage {
    //Type Define
    ERROR("ERROR", 0),
    START("START", 1),
    END("END", 2),
    TURN("TURN", 3),
    EMOTI("EMOTI", 4),
    NAME("NAME", 5),
    MSG("MSG",6);

    private String stringValue;
    private int intValue;

    private TypePackage(String toString, int value) {
        stringValue = toString;
        intValue = value;
    }

    public static TypePackage fromString(String text) {
        for (TypePackage item : TypePackage.values()) {
            if (item.toString().equalsIgnoreCase(text)) {
                return item;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}

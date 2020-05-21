package com.jungle77.paymentsys.domain.enums;

public enum TransactionType{

    AUTHORIZE("AUTHORIZE"), 
    CHARGE("CHARGE"), 
    REFUND("REFUND"), 
    REVERSAL("REVERSAL");

    private String name;

    private TransactionType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static TransactionType fromName(String shortName) {
        for (TransactionType m : values()) {
            if (m.name.equals(shortName)) {
                return m;
            }
        }
        throw new IllegalArgumentException(
                "Enum value is not found  " + shortName);
    }
}
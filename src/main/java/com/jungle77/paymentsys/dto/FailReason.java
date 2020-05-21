package com.jungle77.paymentsys.dto;
public enum FailReason {

    ERR_OK("Processing was successful"),
    ERR_INPUT("Request parameters doesn't pass required validations"),
    ERR_SYSTEM("Unknown error. Transaction failed due to unknown reason");
    private final String description;
    
    private FailReason(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }

}
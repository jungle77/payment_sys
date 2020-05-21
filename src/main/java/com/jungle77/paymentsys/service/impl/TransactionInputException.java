package com.jungle77.paymentsys.service.impl;

public class TransactionInputException extends Exception {
    
    private static final long serialVersionUID = 1L;

    public TransactionInputException(String message) {
        super(message);
    }
    
    public TransactionInputException(String message, Throwable throwable) {
        super(message, throwable);
    }
    
}

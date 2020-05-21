package com.jungle77.paymentsys.service.impl;

import com.jungle77.paymentsys.dto.AuthorizationRequestDto;

public abstract class Validator {
    
    protected Validator nextLevelValidator;
    
    public void setNextLevelValidator(Validator validator) {
        nextLevelValidator = validator;
    }
    
    public abstract ValidationResult validate(AuthorizationRequestDto dto) throws TransactionInputException;
    
}


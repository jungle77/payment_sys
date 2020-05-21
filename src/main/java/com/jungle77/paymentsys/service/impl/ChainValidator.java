package com.jungle77.paymentsys.service.impl;

import org.springframework.stereotype.Component;

@Component
public class ChainValidator {
    
    private Validator validator;
    
    public Validator getAuthorisationValidator() {
        if (validator == null) {
            validator = new InputValidator();
        }
        return validator;
    }
    
}


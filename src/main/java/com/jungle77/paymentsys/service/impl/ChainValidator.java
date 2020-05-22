package com.jungle77.paymentsys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jungle77.paymentsys.repository.MerchandRepository;
import com.jungle77.paymentsys.repository.TransactionRepository;

@Component
public class ChainValidator {
    
    @Autowired private TransactionRepository transactionRepository;
    @Autowired private MerchandRepository merchandRepository;
    
    private Validator validator;
    
    public Validator getValidator() {
         
        if (validator == null) {
            validator = new InputFormatValidator();
            validator.setNextLevelValidator(new InputDataValidator(transactionRepository, merchandRepository));
        }
        
        return validator;
    }
    
}


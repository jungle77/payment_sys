package com.jungle77.paymentsys.service.impl;

import org.apache.commons.validator.routines.RegexValidator;
import org.springframework.stereotype.Component;

import com.jungle77.paymentsys.dto.AuthorizationRequestDto;
import com.jungle77.paymentsys.dto.ChargeRequestDto;
import com.jungle77.paymentsys.dto.RefundRequestDto;
import com.jungle77.paymentsys.dto.ReverseRequestDto;
import com.jungle77.paymentsys.exceptions.TransactionInputException;

@Component
public class InputFormatValidator extends Validator {
    
    private static final String EMAIL_REGEX = "^(.+)@(.+)$";
    
    private static final RegexValidator EMAIL_VALIDATOR = new RegexValidator(EMAIL_REGEX);
    
    public void validate(ValidationResultWrapper wrapper, AuthorizationRequestDto dto) throws TransactionInputException {
        
        // amount validation
        double amount = 0;
        try {
            amount = Double.parseDouble(dto.getAmount());
        } catch (NumberFormatException e) {
            throw new TransactionInputException(dto.getAmount() + " is not a numeric value.", e);
        }
        if (amount <= 0) {
            throw new TransactionInputException(amount + " is not a valid amount value.");
        }
        
        // email validation 
        if(!EMAIL_VALIDATOR.isValid(dto.getCustomerEmail())) {
            throw new TransactionInputException(amount + " is not a valid email.");
        }
    }
    
    public void validate(ValidationResultWrapper wrapper, ChargeRequestDto dto) throws TransactionInputException {
        
        // amount validation
        double amount = 0;
        try {
            amount = Double.parseDouble(dto.getAmount());
        } catch (NumberFormatException e) {
            throw new TransactionInputException(dto.getAmount() + " is not a numeric value.", e);
        }
        if (amount <= 0) {
            throw new TransactionInputException(amount + " is not a valid amount value.");
        }
    }
    
    public void validate(ValidationResultWrapper wrapper, RefundRequestDto dto) throws TransactionInputException {
        
        // amount validation
        double amount = 0;
        try {
            amount = Double.parseDouble(dto.getAmount());
        } catch (NumberFormatException e) {
            throw new TransactionInputException(dto.getAmount() + " is not a numeric value.", e);
        }
        if (amount <= 0) {
            throw new TransactionInputException(amount + " is not a valid amount value.");
        }
    }
    
    public void validate(ValidationResultWrapper wrapper, ReverseRequestDto dto) throws TransactionInputException {
    }
    
    
    
}
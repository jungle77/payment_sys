package com.jungle77.paymentsys.service.impl;

import com.jungle77.paymentsys.dto.AuthorizationRequestDto;
import com.jungle77.paymentsys.dto.ChargeRequestDto;
import com.jungle77.paymentsys.dto.RefundRequestDto;
import com.jungle77.paymentsys.dto.ReverseRequestDto;
import com.jungle77.paymentsys.exceptions.TransactionInputException;

public abstract class Validator {
    
    protected Validator nextLevelValidator;
    
    public void setNextLevelValidator(Validator validator) {
        nextLevelValidator = validator;
    }
    
    public void chainValidate(ValidationResultWrapper wrapper, AuthorizationRequestDto dto) throws TransactionInputException {
        validate(wrapper, dto);
        if (nextLevelValidator != null) {
            nextLevelValidator.chainValidate(wrapper, dto);
        }
    }
    
    public void chainValidate(ValidationResultWrapper wrapper, ChargeRequestDto dto) throws TransactionInputException {
        validate(wrapper, dto);
        if (nextLevelValidator != null) {
            nextLevelValidator.chainValidate(wrapper, dto);
        }
    }
    
    public void chainValidate(ValidationResultWrapper wrapper, RefundRequestDto dto) throws TransactionInputException {
        validate(wrapper, dto);
        if (nextLevelValidator != null) {
            nextLevelValidator.chainValidate(wrapper, dto);
        }
    }
    
    public void chainValidate(ValidationResultWrapper wrapper, ReverseRequestDto dto) throws TransactionInputException {
        validate(wrapper, dto);
        if (nextLevelValidator != null) {
            nextLevelValidator.chainValidate(wrapper, dto);
        }
    }
    
    public abstract void validate(ValidationResultWrapper wrapper, AuthorizationRequestDto dto) throws TransactionInputException;
    public abstract void validate(ValidationResultWrapper wrapper, ChargeRequestDto dto) throws TransactionInputException;
    public abstract void validate(ValidationResultWrapper wrapper, RefundRequestDto dto) throws TransactionInputException;
    public abstract void validate(ValidationResultWrapper wrapper, ReverseRequestDto dto) throws TransactionInputException;
    
}


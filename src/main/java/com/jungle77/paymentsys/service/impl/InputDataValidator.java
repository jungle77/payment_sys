package com.jungle77.paymentsys.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jungle77.paymentsys.domain.Merchand;
import com.jungle77.paymentsys.domain.Transaction;
import com.jungle77.paymentsys.domain.enums.TransactionType;
import com.jungle77.paymentsys.dto.AuthorizationRequestDto;
import com.jungle77.paymentsys.dto.ChargeRequestDto;
import com.jungle77.paymentsys.dto.RefundRequestDto;
import com.jungle77.paymentsys.dto.ReverseRequestDto;
import com.jungle77.paymentsys.exceptions.TransactionInputException;
import com.jungle77.paymentsys.repository.MerchandRepository;
import com.jungle77.paymentsys.repository.TransactionRepository;
import com.mysql.cj.util.StringUtils;

public class InputDataValidator extends Validator {
    
    private TransactionRepository transactionRepository;
    private MerchandRepository merchandRepository;
    
    public InputDataValidator(TransactionRepository transactionRepository, MerchandRepository merchandRepository) {
        this.merchandRepository = merchandRepository;
        this.transactionRepository = transactionRepository;
    }
    
    public void validate(ValidationResultWrapper wrapper, AuthorizationRequestDto dto) throws TransactionInputException {
        
        validateMerchandExists(wrapper, dto.getMerchantId());
        
        validateMerchandIsActive(wrapper);
    }

    public void validate(ValidationResultWrapper wrapper, ChargeRequestDto dto) throws TransactionInputException {
        
        validateTransaction(wrapper, dto.getTransactionId(), TransactionType.AUTHORIZE);
        
        validateMerchandIsActive(wrapper);

    }

    public void validate(ValidationResultWrapper wrapper, RefundRequestDto dto) throws TransactionInputException {
        
        validateTransaction(wrapper, dto.getTransactionId(), TransactionType.CHARGE);
        
        validateMerchandIsActive(wrapper);
        
    }
    
    public void validate(ValidationResultWrapper wrapper, ReverseRequestDto dto) throws TransactionInputException {
        
        validateTransaction(wrapper, dto.getTransactionId(), TransactionType.AUTHORIZE);
        
        validateMerchandIsActive(wrapper);
        
    }
    

    private void validateTransaction(ValidationResultWrapper wrapper, String transactionId, TransactionType transactionType) throws TransactionInputException {

        // transactionId
        if (StringUtils.isNullOrEmpty(transactionId)) {
            throw new TransactionInputException("Not a valid transaction id: " + transactionId);
        }
        Optional<Transaction> parentTransactionOptional = transactionRepository.findById(transactionId);
        if (!parentTransactionOptional.isPresent()) {
            throw new TransactionInputException("Unknown transaction id: " + transactionId);
        }
        Transaction parentTransaction = parentTransactionOptional.get();
        if (parentTransaction.getType() != transactionType) {
            throw new TransactionInputException("Parent transaction is not from type " + transactionType);
        }
        wrapper.setParentTransaction(parentTransaction);
    }
    
    private void validateMerchandIsActive(ValidationResultWrapper wrapper) throws TransactionInputException {
        
        Merchand merchand = wrapper.merchand == null ? wrapper.parentTransaction.getMerchand() : wrapper.merchand;
        
        if (!merchand.getIsActive()) {
            throw new TransactionInputException("Merchand with id " + merchand.getId() + " is not active.");
        }
        wrapper.setMerchand(merchand);
    }
    

    private void validateMerchandExists(ValidationResultWrapper wrapper, String merchandId) throws TransactionInputException {
        Optional<Merchand> merchandOptional = merchandRepository.findById(merchandId);
        if (!merchandOptional.isPresent()) {
            throw new TransactionInputException("Unknown merchand id: " + merchandId);
        }
        wrapper.setMerchand(merchandOptional.get());
    }

    
}
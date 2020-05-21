package com.jungle77.paymentsys.service.impl;

import java.util.Optional;

import org.apache.commons.validator.routines.RegexValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jungle77.paymentsys.domain.Merchand;
import com.jungle77.paymentsys.domain.Transaction;
import com.jungle77.paymentsys.domain.enums.TransactionStatus;
import com.jungle77.paymentsys.domain.enums.TransactionType;
import com.jungle77.paymentsys.dto.AuthorizationRequestDto;
import com.jungle77.paymentsys.dto.ChargeRequestDto;
import com.jungle77.paymentsys.dto.RefundRequestDto;
import com.jungle77.paymentsys.dto.ReverseRequestDto;
import com.jungle77.paymentsys.repository.MerchandRepository;
import com.jungle77.paymentsys.repository.TransactionRepository;

@Component
public class InputValidator extends Validator {
    
    private static final String AMOUNT_REGEX = "^[1-9][0-9]{0,60}$";
    private static final String EMAIL_REGEX = "^(.+)@(.+)$";
    
    private static final RegexValidator EMAIL_VALIDATOR = new RegexValidator(EMAIL_REGEX);
    
    @Autowired
    private TransactionRepository transactionRepository;
    
    @Autowired
    private MerchandRepository merchandRepository;
    
    public ValidationResult validate(AuthorizationRequestDto dto) throws TransactionInputException {
        
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
        
        // merchandId
        Optional<Merchand> merchandOptional = merchandRepository.findById(dto.getMerchantId());
        if (!merchandOptional.isPresent()) {
            throw new TransactionInputException("Unknown merchand id: " + dto.getMerchantId());
        }
        
        Merchand merchand = merchandOptional.get();
        if (!merchand.getIsActive()) {
            throw new TransactionInputException("Merchand with id " + dto.getMerchantId() + " is not active.");
        }
        
        return ValidationResult.builder()
                        .merchand(merchand)
                        .build();
        
    }
    
    public ValidationResult validate(ChargeRequestDto dto) throws TransactionInputException {
        
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
        
        // transactionId
        Optional<Transaction> parentTransactionOptional = transactionRepository.findById(dto.getTransactionId());
        if (!parentTransactionOptional.isPresent()) {
            throw new TransactionInputException("Unknown transaction id: " + dto.getTransactionId());
        }
        Transaction parentTransaction = parentTransactionOptional.get();
        if (parentTransaction.getType() != TransactionType.AUTHORIZE) {
            throw new TransactionInputException("Parent transaction is not from type AUTHORIZE.");
        }
        
        
        // merchand
        Merchand merchand = parentTransaction.getMerchandId();
        if (!merchand.getIsActive()) {
            throw new TransactionInputException("Merchand with id " + merchand.getId() + " is not active.");
        }
        
        return ValidationResult.builder()
                        .parentTransaction(parentTransaction)
                        .build();
    }
    
    public ValidationResult validate(RefundRequestDto dto) throws TransactionInputException {
        
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
        
        // transactionId
        Optional<Transaction> parentTransactionOptional = transactionRepository.findById(dto.getTransactionId());
        if (!parentTransactionOptional.isPresent()) {
            throw new TransactionInputException("Unknown transaction id: " + dto.getTransactionId());
        }
        Transaction parentTransaction = parentTransactionOptional.get();
        if (parentTransaction.getType() != TransactionType.REFUND) {
            throw new TransactionInputException("Parent transaction is not from type REFUND.");
        }
        
        // merchand
        Merchand merchand = parentTransaction.getMerchandId();
        if (!merchand.getIsActive()) {
            throw new TransactionInputException("Merchand with id " + merchand.getId() + " is not active.");
        }
        
        return ValidationResult.builder()
                        .parentTransaction(parentTransaction)
                        .build();
    }
    
    public ValidationResult validate(ReverseRequestDto dto) throws TransactionInputException {
        
        // transactionId
        Optional<Transaction> parentTransactionOptional = transactionRepository.findById(dto.getTransactionId());
        if (!parentTransactionOptional.isPresent()) {
            throw new TransactionInputException("Unknown transaction id: " + dto.getTransactionId());
        }
        Transaction parentTransaction = parentTransactionOptional.get();
        if (parentTransaction.getType() != TransactionType.AUTHORIZE) {
            throw new TransactionInputException("Parent transaction is not from type AUTHORIZE.");
        }

        // merchand
        Merchand merchand = parentTransaction.getMerchandId();
        if (!merchand.getIsActive()) {
            throw new TransactionInputException("Merchand with id " + merchand.getId() + " is not active.");
        }
        
        return ValidationResult.builder()
                        .parentTransaction(parentTransactionOptional.get())
                        .build();
    }
    
    
    
}
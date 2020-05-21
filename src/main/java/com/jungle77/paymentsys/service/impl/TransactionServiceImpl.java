package com.jungle77.paymentsys.service.impl;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jungle77.paymentsys.domain.Merchand;
import com.jungle77.paymentsys.domain.Transaction;
import com.jungle77.paymentsys.domain.enums.TransactionStatus;
import com.jungle77.paymentsys.domain.enums.TransactionType;
import com.jungle77.paymentsys.dto.AuthorizationRequestDto;
import com.jungle77.paymentsys.dto.ChargeRefundRequestDto;
import com.jungle77.paymentsys.dto.FailReason;
import com.jungle77.paymentsys.dto.GeneralResponseDto;
import com.jungle77.paymentsys.dto.ReverseRequestDto;
import com.jungle77.paymentsys.repository.MerchandRepository;
import com.jungle77.paymentsys.repository.TransactionRepository;
import com.jungle77.paymentsys.service.TransactionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired private TransactionRepository transactionRepository;
    @Autowired private MerchandRepository merchandRepository;
    @Autowired private ChainValidator chainValidator;
    @Autowired private InputValidator inputValidator;
    
    public GeneralResponseDto authorize(AuthorizationRequestDto dto) {
        
        try {
            ValidationResult validationResult = inputValidator.validate(dto);
            
            // TODO this could go in a cache
            Merchand merchand = validationResult.getMerchand();
            
            Transaction transaction = Transaction.builder()
                                                .id(UUID.randomUUID().toString())
                                                .amount(new BigDecimal(dto.getAmount()))
                                                .merchandId(merchand)
                                                .customerEmail(dto.getCustomerEmail())
                                                .customerPhone(dto.getCustomerPhone())
                                                .status(TransactionStatus.APPROVED)
                                                .type(TransactionType.AUTHORIZE)
                                                .build();
            
            transactionRepository.save(transaction);
            
            return GeneralResponseDto.builder()
                                    .transactionId(transaction.getId())
                                    .status(TransactionStatus.APPROVED)
                                    .errorCode(FailReason.ERR_OK.getDescription())
                                    .build();
                    
        } catch (TransactionInputException e) {
            log.error("{}", e.getMessage(), e);
            return GeneralResponseDto.builder()
                                    .errorCode(FailReason.ERR_INPUT.toString())
                                    .errorMessage(FailReason.ERR_INPUT.getDescription())
                                    .build();
        } catch (Exception e) {
            log.error("{}", e.getMessage(), e);
            return GeneralResponseDto.builder()
                                    .errorCode(FailReason.ERR_SYSTEM.toString())
                                    .errorMessage(FailReason.ERR_SYSTEM.getDescription())
                                    .build();
        }
    }
    
    public GeneralResponseDto charge(ChargeRefundRequestDto dto) {
        
        try {
            
            ValidationResult validationResult = inputValidator.validate(dto);

            Transaction parentTransaction = validationResult.getParentTransaction();
            
            Transaction transaction = Transaction.builder()
                    .id(UUID.randomUUID().toString())
                    .amount(new BigDecimal(dto.getAmount()))
                    .parentTransaction(parentTransaction)
                    .status(TransactionStatus.APPROVED)
                    .type(TransactionType.CHARGE)
                    .build();
            
            transactionRepository.save(transaction);
            
            return GeneralResponseDto.builder()
                    .transactionId(transaction.getId())
                    .status(TransactionStatus.APPROVED)
                    .errorCode(FailReason.ERR_OK.getDescription())
                    .build();
            
        } catch (TransactionInputException e) {
            log.error("{}", e.getMessage(), e);
            return GeneralResponseDto.builder()
                                    .errorCode(FailReason.ERR_INPUT.toString())
                                    .errorMessage(FailReason.ERR_INPUT.getDescription())
                                    .build();
        } catch (Exception e) {
            log.error("{}", e.getMessage(), e);
            return GeneralResponseDto.builder()
                                    .errorCode(FailReason.ERR_SYSTEM.toString())
                                    .errorMessage(FailReason.ERR_SYSTEM.getDescription())
                                    .build();
        }
    }
    
    public GeneralResponseDto refund(ChargeRefundRequestDto dto) {
        
        try {
            
            ValidationResult validationResult = inputValidator.validate(dto);
    
            Transaction parentTransaction = validationResult.getParentTransaction();
            
            Transaction transaction = Transaction.builder()
                    .id(UUID.randomUUID().toString())
                    .amount(new BigDecimal(dto.getAmount()))
                    .parentTransaction(parentTransaction)
                    .status(TransactionStatus.REFUNDED)
                    .type(TransactionType.REFUND)
                    .build();
            
            return GeneralResponseDto.builder()
                    .transactionId(transaction.getId())
                    .status(TransactionStatus.APPROVED)
                    .errorCode(FailReason.ERR_OK.getDescription())
                    .build();
            
        } catch (TransactionInputException e) {
            log.error("{}", e.getMessage(), e);
            return GeneralResponseDto.builder()
                                    .errorCode(FailReason.ERR_INPUT.getDescription())
                                    .errorMessage(FailReason.ERR_INPUT.getDescription())
                                    .build();
        } catch (Exception e) {
            log.error("{}", e.getMessage(), e);
            return GeneralResponseDto.builder()
                                    .errorCode(FailReason.ERR_SYSTEM.toString())
                                    .errorMessage(FailReason.ERR_SYSTEM.getDescription())
                                    .build();
        }
    }
    
    public GeneralResponseDto reverse(ReverseRequestDto dto) {
        
        try {
            
            ValidationResult validationResult = inputValidator.validate(dto);
    
            Transaction parentTransaction = validationResult.getParentTransaction();
            
            Transaction transaction = Transaction.builder()
                    .id(UUID.randomUUID().toString())
                    .parentTransaction(parentTransaction)
                    .status(TransactionStatus.REVERSED)
                    .type(TransactionType.REVERSAL)
                    .build();
            
            return GeneralResponseDto.builder()
                    .transactionId(transaction.getId())
                    .status(TransactionStatus.APPROVED)
                    .errorCode(FailReason.ERR_OK.getDescription())
                    .build();
            
        } catch (TransactionInputException e) {
            log.error("{}", e.getMessage(), e);
            return GeneralResponseDto.builder()
                                    .errorCode(FailReason.ERR_INPUT.toString())
                                    .errorMessage(FailReason.ERR_INPUT.getDescription())
                                    .build();
        } catch (Exception e) {
            log.error("{}", e.getMessage(), e);
            return GeneralResponseDto.builder()
                                    .errorCode(FailReason.ERR_SYSTEM.toString())
                                    .errorMessage(FailReason.ERR_SYSTEM.getDescription())
                                    .build();
        }
    }
}

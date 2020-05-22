package com.jungle77.paymentsys.service.impl;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jungle77.paymentsys.domain.Transaction;
import com.jungle77.paymentsys.domain.enums.TransactionStatus;
import com.jungle77.paymentsys.domain.enums.TransactionType;
import com.jungle77.paymentsys.dto.AuthorizationRequestDto;
import com.jungle77.paymentsys.dto.ChargeRequestDto;
import com.jungle77.paymentsys.dto.FailReason;
import com.jungle77.paymentsys.dto.GeneralResponseDto;
import com.jungle77.paymentsys.dto.RefundRequestDto;
import com.jungle77.paymentsys.dto.ReverseRequestDto;
import com.jungle77.paymentsys.exceptions.TransactionInputException;
import com.jungle77.paymentsys.repository.TransactionRepository;
import com.jungle77.paymentsys.service.TransactionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired private TransactionRepository transactionRepository;
    @Autowired private ChainValidator chainValidator;
    
    public GeneralResponseDto authorize(AuthorizationRequestDto dto) {
        
        try {
            
            ValidationResultWrapper validationResultWrapper = ValidationResultWrapper.builder().build();
            chainValidator.getValidator().chainValidate(validationResultWrapper, dto);
            
            Transaction transaction = Transaction.builder()
                                                .id(UUID.randomUUID().toString())
                                                .amount(new BigDecimal(dto.getAmount()))
                                                .merchand(validationResultWrapper.getMerchand())
                                                .customerEmail(dto.getCustomerEmail())
                                                .customerPhone(dto.getCustomerPhone())
                                                .status(TransactionStatus.APPROVED)
                                                .type(TransactionType.AUTHORIZE)
                                                .build();
            
            transactionRepository.save(transaction);
            
            return createResponse(transaction);
                    
        } catch (TransactionInputException e) {
            log.error("{}", e.getMessage(), e);
            return createErrorResponse(FailReason.ERR_INPUT.toString(), e.getMessage());
        } catch (Exception e) {
            log.error("{}", e.getMessage(), e);
            return createErrorResponse(FailReason.ERR_SYSTEM.toString(), FailReason.ERR_SYSTEM.getDescription());
        }
    }

    public GeneralResponseDto charge(ChargeRequestDto dto) {
        
        try {
            
            ValidationResultWrapper validationResultWrapper = ValidationResultWrapper.builder().build();
            chainValidator.getValidator().chainValidate(validationResultWrapper, dto);

            Transaction transaction = Transaction.builder()
                                                .id(UUID.randomUUID().toString())
                                                .amount(new BigDecimal(dto.getAmount()))
                                                .parentTransaction(validationResultWrapper.getParentTransaction())
                                                .status(TransactionStatus.APPROVED)
                                                .type(TransactionType.CHARGE)
                                                .build();
            
            transactionRepository.save(transaction);
            
            return createResponse(transaction);
            
        } catch (TransactionInputException e) {
            log.error("{}", e.getMessage(), e);
            return createErrorResponse(FailReason.ERR_INPUT.toString(), e.getMessage());
        } catch (Exception e) {
            log.error("{}", e.getMessage(), e);
            return createErrorResponse(FailReason.ERR_SYSTEM.toString(), FailReason.ERR_SYSTEM.getDescription());
        }
    }
    
    public GeneralResponseDto refund(RefundRequestDto dto) {
        
        try {
            
            ValidationResultWrapper validationResultWrapper = ValidationResultWrapper.builder().build();
            chainValidator.getValidator().chainValidate(validationResultWrapper, dto);
            
            Transaction transaction = Transaction.builder()
                    .id(UUID.randomUUID().toString())
                    .amount(new BigDecimal(dto.getAmount()))
                    .parentTransaction(validationResultWrapper.getParentTransaction())
                    .status(TransactionStatus.REFUNDED)
                    .type(TransactionType.REFUND)
                    .build();
            
            return createResponse(transaction);
            
        } catch (TransactionInputException e) {
            log.error("{}", e.getMessage(), e);
            return createErrorResponse(FailReason.ERR_INPUT.toString(), e.getMessage());
        } catch (Exception e) {
            log.error("{}", e.getMessage(), e);
            return createErrorResponse(FailReason.ERR_SYSTEM.toString(), FailReason.ERR_SYSTEM.getDescription());
        }
    }
    
    public GeneralResponseDto reverse(ReverseRequestDto dto) {
        
        try {
            
            ValidationResultWrapper validationResultWrapper = ValidationResultWrapper.builder().build();
            chainValidator.getValidator().chainValidate(validationResultWrapper, dto);
            
            Transaction transaction = Transaction.builder()
                    .id(UUID.randomUUID().toString())
                    .parentTransaction(validationResultWrapper.getParentTransaction())
                    .status(TransactionStatus.REVERSED)
                    .type(TransactionType.REVERSAL)
                    .build();
            
            return createResponse(transaction);
            
        } catch (TransactionInputException e) {
            log.error("{}", e.getMessage(), e);
            return createErrorResponse(FailReason.ERR_INPUT.toString(), e.getMessage());
        } catch (Exception e) {
            log.error("{}", e.getMessage(), e);
            return createErrorResponse(FailReason.ERR_SYSTEM.toString(), FailReason.ERR_SYSTEM.getDescription());
        }
    }
    
    private GeneralResponseDto createErrorResponse(String errorCode, String errorMessage) {
        return GeneralResponseDto.builder()
                                .errorCode(errorCode)
                                .errorMessage(errorMessage)
                                .build();
    }
    
    private GeneralResponseDto createResponse(Transaction transaction) {
        return GeneralResponseDto.builder()
                                .transactionId(transaction.getId())
                                .status(TransactionStatus.APPROVED)
                                .errorCode(FailReason.ERR_OK.getDescription())
                                .build();
    }
}

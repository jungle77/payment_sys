package com.jungle77.paymentsys.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jungle77.paymentsys.domain.Transaction;
import com.jungle77.paymentsys.dto.AuthorizationRequestDto;
import com.jungle77.paymentsys.dto.AuthorizationResponseDto;
import com.jungle77.paymentsys.dto.ChargeRequestDto;
import com.jungle77.paymentsys.dto.GeneralResponseDto;
import com.jungle77.paymentsys.dto.RefundRequestDto;
import com.jungle77.paymentsys.dto.ReverseRequestDto;
import com.jungle77.paymentsys.repository.TransactionRepository;
import com.jungle77.paymentsys.resource.TransactionResource;
import com.jungle77.paymentsys.service.TransactionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    
    public AuthorizationResponseDto authorize(AuthorizationRequestDto requestDto) {
        
        Optional<Transaction> findById = transactionRepository.findById("dfwef");
        
        log.info("test authorize {}", findById);
        
        return null;
    }
    
    public GeneralResponseDto charge(ChargeRequestDto requestDto) {
        return null;
    }
    
    public GeneralResponseDto refund(RefundRequestDto requestDto) {
        return null;
    }
    
    public GeneralResponseDto reverse(ReverseRequestDto requestDto) {
        return null;
    }
}

package com.jungle77.paymentsys.dto;

import com.jungle77.paymentsys.domain.enums.TransactionStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GeneralResponseDto {
    
    private String transactionId;
    private TransactionStatus status;
    private String errorCode;
    private String errorMessage;
    
}


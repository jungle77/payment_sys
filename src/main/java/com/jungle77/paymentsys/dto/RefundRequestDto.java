package com.jungle77.paymentsys.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefundRequestDto {

    private String transactionId;
    private String amount;
}
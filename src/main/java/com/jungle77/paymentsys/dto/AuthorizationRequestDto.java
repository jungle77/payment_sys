package com.jungle77.paymentsys.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorizationRequestDto {

    private String requestId;
    private String merchantId;
    private String amount;
    private String customerEmail;
    private String customerPhone;
}

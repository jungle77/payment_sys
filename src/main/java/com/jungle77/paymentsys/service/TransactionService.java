package com.jungle77.paymentsys.service;

import com.jungle77.paymentsys.dto.AuthorizationRequestDto;
import com.jungle77.paymentsys.dto.ChargeRefundRequestDto;
import com.jungle77.paymentsys.dto.GeneralResponseDto;
import com.jungle77.paymentsys.dto.ReverseRequestDto;

public interface TransactionService {

    GeneralResponseDto authorize(AuthorizationRequestDto requestDto);
    GeneralResponseDto charge(ChargeRefundRequestDto requestDto);
    GeneralResponseDto refund(ChargeRefundRequestDto requestDto);
    GeneralResponseDto reverse(ReverseRequestDto requestDto);

}


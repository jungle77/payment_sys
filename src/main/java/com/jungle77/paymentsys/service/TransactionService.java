package com.jungle77.paymentsys.service;

import com.jungle77.paymentsys.dto.AuthorizationRequestDto;
import com.jungle77.paymentsys.dto.ChargeRequestDto;
import com.jungle77.paymentsys.dto.GeneralResponseDto;
import com.jungle77.paymentsys.dto.RefundRequestDto;
import com.jungle77.paymentsys.dto.ReverseRequestDto;

public interface TransactionService {

    GeneralResponseDto authorize(AuthorizationRequestDto requestDto);
    GeneralResponseDto charge(ChargeRequestDto requestDto);
    GeneralResponseDto refund(RefundRequestDto requestDto);
    GeneralResponseDto reverse(ReverseRequestDto requestDto);

}


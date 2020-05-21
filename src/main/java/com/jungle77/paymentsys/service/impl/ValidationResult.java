package com.jungle77.paymentsys.service.impl;

import com.jungle77.paymentsys.domain.Merchand;
import com.jungle77.paymentsys.domain.Transaction;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ValidationResult {
    private Transaction parentTransaction;
    private Merchand merchand;
}

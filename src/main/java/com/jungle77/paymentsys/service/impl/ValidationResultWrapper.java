package com.jungle77.paymentsys.service.impl;

import com.jungle77.paymentsys.domain.Merchand;
import com.jungle77.paymentsys.domain.Transaction;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ValidationResultWrapper {
    public Transaction parentTransaction;
    public Merchand merchand;
}

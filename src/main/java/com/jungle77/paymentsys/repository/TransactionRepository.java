package com.jungle77.paymentsys.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jungle77.paymentsys.domain.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,String> {
    
    List<Transaction> findByCreateDateLessThan(Timestamp timestamp);
    
}


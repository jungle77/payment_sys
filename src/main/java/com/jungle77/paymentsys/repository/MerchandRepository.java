package com.jungle77.paymentsys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jungle77.paymentsys.domain.Merchand;

public interface MerchandRepository extends JpaRepository<Merchand,String> {
    
}

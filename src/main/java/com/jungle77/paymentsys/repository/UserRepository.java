package com.jungle77.paymentsys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jungle77.paymentsys.domain.User;

public interface UserRepository extends JpaRepository<User,String> {
    
}

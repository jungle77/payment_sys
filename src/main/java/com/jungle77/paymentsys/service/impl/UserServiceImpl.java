package com.jungle77.paymentsys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jungle77.paymentsys.domain.User;
import com.jungle77.paymentsys.repository.UserRepository;
import com.jungle77.paymentsys.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    public void saveUsers(List<User> users) {
        userRepository.saveAll(users);
    }

}
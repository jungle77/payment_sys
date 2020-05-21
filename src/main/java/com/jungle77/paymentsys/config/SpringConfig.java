package com.jungle77.paymentsys.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableScheduling
@ComponentScan(basePackages= {"util", "com.jungle77.paymentsys.repository"})
public class SpringConfig {
    
}
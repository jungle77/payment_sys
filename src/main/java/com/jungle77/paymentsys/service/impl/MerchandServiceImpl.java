package com.jungle77.paymentsys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jungle77.paymentsys.domain.Merchand;
import com.jungle77.paymentsys.repository.MerchandRepository;
import com.jungle77.paymentsys.service.MerchandService;

@Service
public class MerchandServiceImpl implements MerchandService{

    @Autowired
    private MerchandRepository merchandRepository;
    
    public void saveMerchands(List<Merchand> merchands) {
        merchandRepository.saveAll(merchands);
    }

}

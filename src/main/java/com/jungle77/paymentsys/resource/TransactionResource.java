package com.jungle77.paymentsys.resource;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jungle77.paymentsys.dto.AuthorizationRequestDto;
import com.jungle77.paymentsys.dto.AuthorizationResponseDto;
import com.jungle77.paymentsys.dto.ChargeRequestDto;
import com.jungle77.paymentsys.dto.GeneralResponseDto;
import com.jungle77.paymentsys.dto.RefundRequestDto;
import com.jungle77.paymentsys.dto.ReverseRequestDto;
import com.jungle77.paymentsys.service.TransactionService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/v1/tx")
@Api(value = "Transaction endpoints for authorization and status check")
@Slf4j
public class TransactionResource {

    private final static String AUTH_IN    = "AUTH_IN: {}";
    private final static String CHARGE_IN  = "CHARGE_IN: {}";
    private final static String REFUND_IN  = "REFUND_IN: {}";
    private final static String REVERSE_IN = "REVERSE_IN: {}";
    
    @Autowired
    private TransactionService transactionService;
    
    @ResponseBody
    @PostMapping(value = "/authorization/", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public AuthorizationResponseDto authorizeTransaction(@RequestBody AuthorizationRequestDto requestDto) {

        log.info(TransactionResource.AUTH_IN, requestDto);

        return transactionService.authorize(requestDto);
        
    }
    
    @ResponseBody
    @PostMapping(value = "/charge/", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public GeneralResponseDto chargeTransaction(@RequestBody ChargeRequestDto requestDto) {

        log.info(TransactionResource.CHARGE_IN, requestDto);

        return transactionService.charge(requestDto);
        
    }
    
    @ResponseBody
    @PostMapping(value = "/refund/", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public GeneralResponseDto refundTransaction(@RequestBody RefundRequestDto requestDto) {

        log.info(TransactionResource.REFUND_IN, requestDto);

        return transactionService.refund(requestDto);
        
    }
    
    @ResponseBody
    @PostMapping(value = "/reverse/", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public GeneralResponseDto reverseTransaction(@RequestBody ReverseRequestDto requestDto) {

        log.info(TransactionResource.REVERSE_IN, requestDto);

        return transactionService.reverse(requestDto);
        
    }
    
    
}

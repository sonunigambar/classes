package com.services.investmentservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class Investmentservice {

    @Autowired
    DownStreamService downStreamService;


    public ResponseEntity<String> createInvestment(String userId, Double amount) {
        return ResponseEntity.ok(downStreamService.callWalletServiceDeductAmount(userId, amount));

    }


}

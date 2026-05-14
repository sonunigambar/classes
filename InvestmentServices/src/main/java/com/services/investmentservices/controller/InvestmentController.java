package com.services.investmentservices.controller;

import com.services.investmentservices.entity.Investment;
import com.services.investmentservices.service.Investmentservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/investment")
public class InvestmentController {

    @Autowired
    Investmentservice investmentservice;

    @GetMapping("/investment/{userId}")
    public ResponseEntity<String> createInvestment(@PathVariable String userId, @RequestParam Double amount) throws InterruptedException {
        return investmentservice.createInvestment(userId, amount);
    }

    @GetMapping("/investment/kafka2/{userId}")
    public ResponseEntity<String> createInvestmentUisngEventDriven(@PathVariable String userId, @RequestParam Double amount){
        return investmentservice.createInvestmentKafka(userId, amount);
    }

    @GetMapping("/getPortfolio/{userId}")
    public Investment getPortFolio(@PathVariable String userId){
        return investmentservice.getPortFolio(userId);
    }

}

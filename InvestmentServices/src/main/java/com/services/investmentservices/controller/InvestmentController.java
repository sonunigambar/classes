package com.services.investmentservices.controller;

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
    public ResponseEntity<String> createInvestment(@PathVariable String userId, @RequestParam Double amount){
        return investmentservice.createInvestment(userId, amount);
    }

    @GetMapping("/getPortfolio")
    public String getPortFolio(@PathVariable String userId){
        return "Investment details here";
    }

}

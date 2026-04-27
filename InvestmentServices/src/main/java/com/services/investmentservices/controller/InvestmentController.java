package com.services.investmentservices.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/investment")
public class InvestmentController {

    @GetMapping("/investment")
    public String createInvestment(@PathVariable String userId, @RequestParam Double amount){
        return "Investment created";
    }

    @GetMapping("/getPortfolio")
    public String getPortFolio(@PathVariable String userId){
        return "Investment details here";
    }

}

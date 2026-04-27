package com.services.walletservice.controller;

import com.services.walletservice.entity.Wallet;
import com.services.walletservice.service.WalletService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {

    WalletService walletService;

    @GetMapping("/add-money")
    public Wallet add(@PathVariable String userId, @PathVariable Double amount) {
        return walletService.add(userId, amount);
    }

    /*@GetMapping("/deduct-money")
    public String deduct(@PathVariable String userId, @PathVariable Double amount) {
        walletService.deduct(userId, amount);
    }

    @GetMapping("/balance")
    public String getBalance(@PathVariable String userId) {
        walletService.getBalance(userId);
    }*/
}

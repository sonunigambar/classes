package com.services.walletservice.controller;

import com.services.walletservice.entity.Wallet;
import com.services.walletservice.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    WalletService walletService;

    @GetMapping("/add-money/{userId}/{amount}")
    public String add(@PathVariable String userId, @PathVariable Double amount) {
        return walletService.add(userId, amount);
    }

    @GetMapping("/test")
    public String test() {
        return "hi i am wallet service";
    }

    @GetMapping("/deduct-money/{userId}/{amount}")
    public ResponseEntity<String> deduct(@PathVariable String userId, @PathVariable Double amount) throws InterruptedException {
//        Thread.sleep(10000);
        String msg = walletService.deduct(userId, amount);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping("/balance/{userId}")
    public Double getBalance(@PathVariable String userId) {
        return walletService.getBalance(userId);
    }
}
package com.services.walletservice.controller;

import com.services.walletservice.entity.Wallet;
import com.services.walletservice.service.WalletService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    WalletService walletService;

    @GetMapping("/add-money")
    public Wallet add(@PathVariable String userId, @PathVariable Double amount) {
        return walletService.add(userId, amount);
    }

    @GetMapping("/test")
    public String test() {
        return "hi i am wallet service";
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

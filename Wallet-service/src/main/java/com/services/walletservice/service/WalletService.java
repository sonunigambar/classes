package com.services.walletservice.service;

import com.services.walletservice.entity.Wallet;
import com.services.walletservice.repo.WalletRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    @Autowired
    WalletRepo repo;

    public Wallet add(String userId, Double amount) {
        Wallet savedWallet = null;
        Wallet walletDetails = repo.findByUserId(userId).orElse(new Wallet());
        if(amount >0){
            Double existingMoney = walletDetails.getMoney();
            walletDetails.setMoney(existingMoney+amount);
            savedWallet= repo.save(walletDetails);
        }
    return savedWallet;
    }

    /*public String deduct(String userId, Double amount) {

    }

    public Double getBalance(String userId) {
    }*/
}

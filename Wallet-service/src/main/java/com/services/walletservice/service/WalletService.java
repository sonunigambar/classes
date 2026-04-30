package com.services.walletservice.service;

import com.services.walletservice.entity.Wallet;
import com.services.walletservice.repo.WalletRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WalletService {

    @Autowired
    WalletRepo repo;

    public String add(String userId, Double amount) {
       if(amount == null || amount < 0) {
           throw new IllegalArgumentException("Invalid Amount");
       }
        Wallet wallet = repo.findByUserId(userId).orElse(null);
       if(wallet == null) {
           wallet = new Wallet();
           wallet.setUserId(userId);
           wallet.setMoney(amount);
           repo.save(wallet);
       }else{
            wallet.setMoney(wallet.getMoney()+amount);
            repo.save(wallet);
       }
       return "Amount added successfully Into Wallet";
    }

   public String deduct(String userId, Double amount) {
       if(amount == null || amount < 0) {
           throw new IllegalArgumentException("Invalid Amount");
       }
       Wallet wallet = repo.findByUserId(userId).orElseThrow(() -> new RuntimeException("Wallet Not Found"));
       Double currentBalance = wallet.getMoney();
       if(currentBalance < amount) {
           return  "Insufficient Balance";
       }
       wallet.setMoney(currentBalance-amount);
       repo.save(wallet);
       return "Amount Deducted Succesfully";
   }

    public Double getBalance(String userId) {
        return repo.findByUserId(userId)
                .map(Wallet::getMoney)
                .orElse(0.0);
    }
}

package com.services.walletservice.repo;

import com.services.walletservice.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepo extends JpaRepository<Wallet, Integer> {
    Optional <Wallet> findByUserId(String userId);
}

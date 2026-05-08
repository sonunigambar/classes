package com.services.investmentservices.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Service
public class DownStreamService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WebClient.Builder builder;

    @Retry(name = "walletServiceRetry", fallbackMethod = "walletFallback")
    @CircuitBreaker(name = "walletService")
    public String callWalletServiceDeductAmountWebClient(String userId, Double amount) {
        System.out.println("inside actual method");
        final String URL = "http://WALLET-SERVICE/wallet/deduct-money/{userId}/{amount}";
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("amount", amount);
        String response = builder.build()
                .get()
                .uri(URL,params)
                .retrieve()
                .bodyToMono(String.class).block();
        System.out.println(response);
        return "SUCCESS";

    }

    @Retry(name = "walletServiceRetry", fallbackMethod = "walletFallback")
    @CircuitBreaker(name = "walletService")
    public String callWalletServiceDeductAmount(String userId, Double amount) {
        System.out.println("inside actual method");
        final String URL = "http://WALLET-SERVICE/wallet/deduct-money/{userId}/{amount}";
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("amount", amount);
        String body = restTemplate.getForEntity(URL, String.class, params).getBody();
        return "SUCCESS";
        //408: Request timeout
        //504: gateway Timeout
    }

    public String walletFallback(String userId, Double amount, Throwable ex){
        System.out.println(ex);
        System.out.println("Fallback Method call"+ex.getMessage());
        return ex.getMessage();
    }

    public String compentiateTxWalletServiceAddAmount(String userId, Double amount) {
        System.out.println("inside actual method");
        final String URL = "http://WALLET-SERVICE/wallet/add-money/{userId}/{amount}";
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("amount", amount);
        return  restTemplate.getForEntity(URL, String.class, params).getBody();
    }

    public String callTransctionService() {
        return "SUCCESS";
    }
}

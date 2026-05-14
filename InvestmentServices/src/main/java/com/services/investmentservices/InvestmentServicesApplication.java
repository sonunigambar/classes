package com.services.investmentservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class InvestmentServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvestmentServicesApplication.class, args);
    }

}

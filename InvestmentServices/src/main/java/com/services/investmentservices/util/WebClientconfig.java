package com.services.investmentservices.util;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientconfig {

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientbuilder() {
        return WebClient.builder();
    }

}

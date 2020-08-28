package com.lhfioravanso.winesalesimprover.integration;

import com.lhfioravanso.winesalesimprover.domain.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class PurchaseHistoryService {

    private final Environment environment;
    private final RestTemplate restTemplate;

    @Autowired
    public PurchaseHistoryService(Environment environment, RestTemplate restTemplate) {
        this.environment = environment;
        this.restTemplate = restTemplate;
    }

    public List<Purchase> getPurchases(){
        String serviceUrl = Objects.requireNonNull(environment.getProperty("purchase.history.service.url"));

        ResponseEntity<Purchase[]> response= restTemplate.exchange(serviceUrl, HttpMethod.GET, null, Purchase[].class);
        return Arrays.asList(response.getBody());
    }
}

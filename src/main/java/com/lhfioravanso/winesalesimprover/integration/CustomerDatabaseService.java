package com.lhfioravanso.winesalesimprover.integration;

import com.lhfioravanso.winesalesimprover.entity.Customer;
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
public class CustomerDatabaseService {

    private final Environment environment;
    private final RestTemplate restTemplate;

    @Autowired
    public CustomerDatabaseService(Environment environment, RestTemplate restTemplate) {
        this.environment = environment;
        this.restTemplate = restTemplate;
    }

    public List<Customer> getCustomers() {
        String serviceUrl = environment.getProperty("customer.service.url");

        try {
            ResponseEntity<Customer[]> response= restTemplate.exchange(Objects.requireNonNull(serviceUrl), HttpMethod.GET, null, Customer[].class);
            return Arrays.asList(response.getBody());
        } catch (Exception ex){
            ex.toString();
        }

        return null;
    }


}

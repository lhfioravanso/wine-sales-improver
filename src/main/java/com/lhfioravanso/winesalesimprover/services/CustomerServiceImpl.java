package com.lhfioravanso.winesalesimprover.services;

import com.lhfioravanso.winesalesimprover.entity.Customer;
import com.lhfioravanso.winesalesimprover.integration.CustomerDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDatabaseService customerDatabaseService;

    @Autowired
    public CustomerServiceImpl(CustomerDatabaseService customerDatabaseService) {
        this.customerDatabaseService = customerDatabaseService;
    }


    @Override
    public List<Customer> listCustomersSortedByHighestTotalPurchaseValues() {
        return customerDatabaseService.getCustomers();
    }
}

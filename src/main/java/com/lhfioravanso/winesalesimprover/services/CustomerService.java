package com.lhfioravanso.winesalesimprover.services;

import com.lhfioravanso.winesalesimprover.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> listCustomersSortedByHighestTotalPurchaseValues();
}

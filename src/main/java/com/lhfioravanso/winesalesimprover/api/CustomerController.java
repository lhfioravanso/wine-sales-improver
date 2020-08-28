package com.lhfioravanso.winesalesimprover.api;

import com.lhfioravanso.winesalesimprover.dto.CustomerWithFidelityResponseDto;
import com.lhfioravanso.winesalesimprover.dto.CustomerWithHighestPurchaseByYearResponseDto;
import com.lhfioravanso.winesalesimprover.dto.CustomerWithTotalPurchaseValueResponseDto;
import com.lhfioravanso.winesalesimprover.dto.WineRecommendationResponseDto;
import com.lhfioravanso.winesalesimprover.services.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "customer")
@RequestMapping(value = "api/v1/customer", produces = "application/json")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value="list of customers ordained by highest total purchases", response = CustomerWithTotalPurchaseValueResponseDto[].class)
    @GetMapping("/highest-total-purchases")
    public ResponseEntity<?> highest() {
        return ResponseEntity.ok(this.customerService.listCustomersByHighestTotalPurchaseValues());
    }

    @ApiOperation(value="returns the customer with most expensive unique purchase in a specific year", response = CustomerWithHighestPurchaseByYearResponseDto.class)
    @GetMapping("/highest/{year}")
    public ResponseEntity<?> highestByYear(@PathVariable String year) {
        return ResponseEntity.ok(this.customerService.getCustomerWithHighestPurchaseByYear(year));
    }

    @ApiOperation(value="list of customers ordained by fidelity", response = CustomerWithFidelityResponseDto[].class)
    @GetMapping("/fidelity")
    public ResponseEntity<?> fidelity() {
        return ResponseEntity.ok(this.customerService.listCustomersByFidelity());
    }

    @ApiOperation(value="returns a wine recommendation by purchase history of specific customer", response = WineRecommendationResponseDto.class)
    @GetMapping("/recommendation/{customerId}")
    public ResponseEntity<?> recommendation(@PathVariable int customerId) {
        return ResponseEntity.ok(this.customerService.wineRecommendation(customerId));
    }

}

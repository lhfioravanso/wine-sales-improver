package com.lhfioravanso.winesalesimprover.api;

import com.lhfioravanso.winesalesimprover.services.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @ApiOperation(value="listCustomersSortedByHighestTotalPurchaseValues", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customers found.")
    })
    @GetMapping()
    public ResponseEntity<?> listCustomersOrdenedByTotalOfPurchases() {
        return ResponseEntity.ok(this.customerService.listCustomersSortedByHighestTotalPurchaseValues());
    }

}

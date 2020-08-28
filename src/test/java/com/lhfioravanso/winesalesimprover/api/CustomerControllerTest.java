package com.lhfioravanso.winesalesimprover.api;

import com.lhfioravanso.winesalesimprover.dto.CustomerWithFidelityResponseDto;
import com.lhfioravanso.winesalesimprover.dto.CustomerWithHighestPurchaseByYearResponseDto;
import com.lhfioravanso.winesalesimprover.dto.CustomerWithTotalPurchaseValueResponseDto;
import com.lhfioravanso.winesalesimprover.dto.WineRecommendationResponseDto;
import com.lhfioravanso.winesalesimprover.services.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    @Mock
    public CustomerService customerService;

    @InjectMocks
    public CustomerController customerController;


    @Test
    public void shouldReturnListOfCustomerWithTotalPurchaseValue() {

        List<CustomerWithTotalPurchaseValueResponseDto> response = new ArrayList<>();

        response.add(new CustomerWithTotalPurchaseValueResponseDto(1, "test1", "test1", new BigDecimal(100)));
        response.add(new CustomerWithTotalPurchaseValueResponseDto(2, "test2", "test2", new BigDecimal(200)));
        response.add(new CustomerWithTotalPurchaseValueResponseDto(3, "test3", "test3", new BigDecimal(300)));

        Mockito.when(customerService.listCustomersByHighestTotalPurchaseValues()).thenReturn(response);

        ResponseEntity<?> resp = customerController.highest();
        assertEquals(resp.getStatusCode(), HttpStatus.OK);
        assertNotNull(resp.getBody());
        assertEquals(3, ((ArrayList<?>) resp.getBody()).size());
    }

    @Test
    public void shouldReturnCustomerWithHighestPurchaseByYear() {
        String year = "2016";

        CustomerWithHighestPurchaseByYearResponseDto responseDto = new CustomerWithHighestPurchaseByYearResponseDto(
          1, "test", "123", new BigDecimal(100)
        );

        Mockito.when(customerService.getCustomerWithHighestPurchaseByYear(year)).thenReturn(responseDto);

        ResponseEntity<?> resp = customerController.highestByYear(year);
        assertEquals(resp.getStatusCode(), HttpStatus.OK);
        assertNotNull(resp.getBody());
    }

    @Test
    public void fidelity() {
        List<CustomerWithFidelityResponseDto> response = new ArrayList<>();

        response.add(new CustomerWithFidelityResponseDto(1, "test1", "test1", 10));
        response.add(new CustomerWithFidelityResponseDto(2, "test2", "test2", 15));
        response.add(new CustomerWithFidelityResponseDto(3, "test3", "test3", 30));

        Mockito.when(customerService.listCustomersByFidelity()).thenReturn(response);

        ResponseEntity<?> resp = customerController.fidelity();
        assertEquals(resp.getStatusCode(), HttpStatus.OK);
        assertNotNull(resp.getBody());
        assertEquals(3, ((ArrayList<?>) resp.getBody()).size());
    }

    @Test
    public void recommendation() {
        int customerId = 1;

        WineRecommendationResponseDto responseDto = new WineRecommendationResponseDto(
                "test", "test", "test", "test", "test", new BigDecimal(10)
        );

        Mockito.when(customerService.wineRecommendation(customerId)).thenReturn(responseDto);

        ResponseEntity<?> resp = customerController.recommendation(customerId);
        assertEquals(resp.getStatusCode(), HttpStatus.OK);
        assertNotNull(resp.getBody());
    }
}
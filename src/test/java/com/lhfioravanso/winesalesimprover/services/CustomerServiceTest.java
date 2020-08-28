package com.lhfioravanso.winesalesimprover.services;

import com.lhfioravanso.winesalesimprover.domain.Customer;
import com.lhfioravanso.winesalesimprover.domain.Item;
import com.lhfioravanso.winesalesimprover.domain.Purchase;
import com.lhfioravanso.winesalesimprover.dto.CustomerWithFidelityResponseDto;
import com.lhfioravanso.winesalesimprover.dto.CustomerWithHighestPurchaseByYearResponseDto;
import com.lhfioravanso.winesalesimprover.dto.CustomerWithTotalPurchaseValueResponseDto;
import com.lhfioravanso.winesalesimprover.dto.WineRecommendationResponseDto;
import com.lhfioravanso.winesalesimprover.integration.CustomerDatabaseService;
import com.lhfioravanso.winesalesimprover.integration.PurchaseHistoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)

public class CustomerServiceTest {

    @Mock
    public CustomerDatabaseService customerDatabaseService;

    @Mock
    public PurchaseHistoryService purchaseHistoryService;

    @InjectMocks
    public CustomerServiceImpl customerService;

    private List<Customer> customers;
    private List<Purchase> purchases;

    @Before
    public void setup() {
        this.purchases = new ArrayList<>();
        this.customers = new ArrayList<>();

        Customer customer01 = new Customer(1, "123", "test1");
        Customer customer02 = new Customer(2, "456", "test2");
        Customer customer03 = new Customer(3, "789", "test3");

        this.customers.add(customer01);
        this.customers.add(customer02);
        this.customers.add(customer03);

        List<Item> winesPurchasedByCustomer01 = new ArrayList<>();
        List<Item> winesPurchasedByCustomer02 = new ArrayList<>();
        List<Item> winesPurchasedByCustomer03 = new ArrayList<>();

        winesPurchasedByCustomer01.add(new Item("Casa Silva Reserva",
                "Chardonnay",
                "Chile",
                "Branco",
                "2016",
                new BigDecimal(79)));
        winesPurchasedByCustomer01.add(new Item("Casa Silva Reserva",
                "Chardonnay",
                "Chile",
                "Branco",
                "2016",
                new BigDecimal(79)));

        winesPurchasedByCustomer02.add(new Item("Casa Silva Reserva",
                "Chardonnay",
                "Chile",
                "Branco",
                "2016",
                new BigDecimal(79)));

        winesPurchasedByCustomer03.add(new Item("Casa Silva Reserva",
                "Chardonnay",
                "Chile",
                "Branco",
                "2016",
                new BigDecimal(79)));

        for (int i = 0; i < 10; i++) {
            winesPurchasedByCustomer01.add(new Item("Vinho Recomendado Test",
                    "Chardonnay",
                    "Chile",
                    "Branco",
                    "2016",
                    new BigDecimal(79)));
        }



        //2016
        this.purchases.add(new Purchase("123","19-02-2016","123", new BigDecimal(100), winesPurchasedByCustomer01));
        this.purchases.add(new Purchase("456","19-02-2016","456", new BigDecimal(150), winesPurchasedByCustomer02));
        this.purchases.add(new Purchase("789","19-02-2016","789", new BigDecimal(200), winesPurchasedByCustomer03));

        //2020
        this.purchases.add(new Purchase("123","19-02-2020","123", new BigDecimal(100), winesPurchasedByCustomer01));
        this.purchases.add(new Purchase("123","19-02-2020","123", new BigDecimal(450), winesPurchasedByCustomer01));
        this.purchases.add(new Purchase("456","19-02-2020","456", new BigDecimal(150), winesPurchasedByCustomer02));
        this.purchases.add(new Purchase("789","19-02-2020","789", new BigDecimal(200), winesPurchasedByCustomer03));
    }

    @Test
    public void shouldReturnZeroCustomersByHighestValue() {
        List<CustomerWithTotalPurchaseValueResponseDto> response = customerService.listCustomersByHighestTotalPurchaseValues();
        assertEquals(0, response.size());
    }

    @Test
    public void shouldReturnListOfCustomerOrdenedTotalPurchaseValue(){
        Mockito.when(customerDatabaseService.getCustomers()).thenReturn(this.customers);
        Mockito.when(purchaseHistoryService.getPurchases()).thenReturn(this.purchases);

        List<CustomerWithTotalPurchaseValueResponseDto> response = customerService.listCustomersByHighestTotalPurchaseValues();
        assertEquals(3, response.size());
        assertEquals("123", response.get(0).getCpf());
        assertEquals(new BigDecimal(650), response.get(0).getTotalPurchaseValue());
        assertEquals(new BigDecimal(400), response.get(1).getTotalPurchaseValue());
        assertEquals(new BigDecimal(300), response.get(2).getTotalPurchaseValue());
    }

    @Test
    public void shouldReturnCustomerWithHighestUniquePurchaseByYear2016() {
        Mockito.when(customerDatabaseService.getCustomers()).thenReturn(customers);
        Mockito.when(purchaseHistoryService.getPurchases()).thenReturn(purchases);

        CustomerWithHighestPurchaseByYearResponseDto response = customerService.getCustomerWithHighestPurchaseByYear("2016");
        assertEquals(new BigDecimal(200), response.getHighestPurchaseValue());
        assertEquals("789", response.getCpf());
    }

    @Test
    public void listCustomersByFidelity() {
        Mockito.when(customerDatabaseService.getCustomers()).thenReturn(customers);
        Mockito.when(purchaseHistoryService.getPurchases()).thenReturn(purchases);

        List<CustomerWithFidelityResponseDto> response = customerService.listCustomersByFidelity();
        assertEquals(3, response.size());
        assertEquals("123", response.get(0).getCpf());
        assertEquals(3, response.get(0).getAmountOfPurchases());
        assertEquals(2, response.get(1).getAmountOfPurchases());
    }

    @Test
    public void wineRecommendation() {
        Mockito.when(customerDatabaseService.getCustomers()).thenReturn(customers);
        Mockito.when(purchaseHistoryService.getPurchases()).thenReturn(purchases);

        WineRecommendationResponseDto response = customerService.wineRecommendation(1);
        assertEquals("Vinho Recomendado Test", response.getProduto());
    }
}
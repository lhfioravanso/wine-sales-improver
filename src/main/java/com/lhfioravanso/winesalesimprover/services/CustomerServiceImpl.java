package com.lhfioravanso.winesalesimprover.services;

import com.lhfioravanso.winesalesimprover.domain.Item;
import com.lhfioravanso.winesalesimprover.dto.*;
import com.lhfioravanso.winesalesimprover.domain.Customer;
import com.lhfioravanso.winesalesimprover.domain.Purchase;
import com.lhfioravanso.winesalesimprover.integration.CustomerDatabaseService;
import com.lhfioravanso.winesalesimprover.integration.PurchaseHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDatabaseService customerDatabaseService;
    private final PurchaseHistoryService purchaseHistoryService;

    @Autowired
    public CustomerServiceImpl(CustomerDatabaseService customerDatabaseService, PurchaseHistoryService purchaseHistoryService) {
        this.customerDatabaseService = customerDatabaseService;
        this.purchaseHistoryService = purchaseHistoryService;
    }

    @Override
    public List<CustomerWithTotalPurchaseValueResponseDto> listCustomersByHighestTotalPurchaseValues() {

        List<CustomerWithTotalPurchaseValueResponseDto> response = new ArrayList<>();

        Map<String, List<Purchase>> groupedPurchasesByCustomer = groupPurchasesByCustomers(purchaseHistoryService.getPurchases());
        List<Customer> customers = customerDatabaseService.getCustomers();

        BigDecimal totalCustomer;
        for (Customer customer: customers) {
            totalCustomer = getSumOfPurchases(groupedPurchasesByCustomer.get(customer.getCpf()));

            response.add(new CustomerWithTotalPurchaseValueResponseDto(
                    customer.getId(), customer.getNome(), customer.getCpf(), totalCustomer)
            );
        }

        return response.stream().
                sorted(Comparator.comparing(CustomerWithTotalPurchaseValueResponseDto::getTotalPurchaseValue).reversed()).
                collect(Collectors.toList());
    }

    @Override
    public CustomerWithHighestPurchaseByYearResponseDto getCustomerWithHighestPurchaseByYear(String year) {

        List<Purchase> uniquePurchasesByYear = getUniquePurchasesByYear(purchaseHistoryService.getPurchases(), year);

        Optional<Purchase> highestPurchase = uniquePurchasesByYear.stream().max(Comparator.comparing(Purchase::getValorTotal));

        if (highestPurchase.isPresent()){
            List<Customer> customers = customerDatabaseService.getCustomers();

            Customer customerWithHighestPurchaseByYear = customers.stream().
                    filter(customer -> customer.getCpf().equals(highestPurchase.get().getCliente())).
                    findFirst().get();

            return new CustomerWithHighestPurchaseByYearResponseDto(
                            customerWithHighestPurchaseByYear.getId(),
                            customerWithHighestPurchaseByYear.getNome(),
                            customerWithHighestPurchaseByYear.getCpf(),
                            highestPurchase.get().getValorTotal());

        }

        return null;
    }

    @Override
    public List<CustomerWithFidelityResponseDto> listCustomersByFidelity() {

        Map<String, List<Purchase>> groupedPurchasesByCustomer = groupPurchasesByCustomers(purchaseHistoryService.getPurchases());
        List<Customer> customers = customerDatabaseService.getCustomers();

        List<CustomerWithFidelityResponseDto> response = new ArrayList<>();

        int countOfPurchases;
        for (Customer customer: customers) {
            countOfPurchases = groupedPurchasesByCustomer.get(customer.getCpf()).size();
            response.add(new CustomerWithFidelityResponseDto(customer.getId(), customer.getNome(), customer.getCpf(), countOfPurchases));
        }

        return response.stream().
                sorted(Comparator.comparing(CustomerWithFidelityResponseDto::getAmountOfPurchases).reversed()).
                collect(Collectors.toList());
    }

    @Override
    public WineRecommendationResponseDto wineRecommendation(int customerId) {

        List<Customer> customers = customerDatabaseService.getCustomers();
        Customer customer = customers.stream().filter(c -> c.getId() == customerId).findFirst().get();

        Map<String, List<Purchase>> groupedPurchasesByCustomer = groupPurchasesByCustomers(purchaseHistoryService.getPurchases());
        List<Purchase> purchases = groupedPurchasesByCustomer.get(customer.getCpf());

        Item mostFrequentWine = getMostFrequentWine(purchases);

        return new WineRecommendationResponseDto(
                mostFrequentWine.getProduto(), mostFrequentWine.getVariedade(), mostFrequentWine.getPais(),
                mostFrequentWine.getCategoria(), mostFrequentWine.getSafra(), mostFrequentWine.getPreco()
        );
    }


    private Item getMostFrequentWine(List<Purchase> customerPurchases){
        List<Item> wines = new ArrayList<>();
        for (Purchase purchase: customerPurchases) {
            wines.addAll(purchase.getItens());
        }

        int mostFrequent = 0;
        Item mostFrequentWine = null;

        Map<String, List<Item>> groupedWines = wines.stream().collect(Collectors.groupingBy(Item::getProduto));
        for (var curr: groupedWines.entrySet()) {

            if (curr.getValue().size() > mostFrequent){
                mostFrequent = curr.getValue().size();
                mostFrequentWine = curr.getValue().get(0);
            }
        }

        return mostFrequentWine;
    }

    private Map<String, List<Purchase>> groupPurchasesByCustomers(List<Purchase> purchases) {
        return purchases.stream().collect(Collectors.groupingBy(Purchase::getCliente));
    }

    private BigDecimal getSumOfPurchases(List<Purchase> purchases) {
        return purchases.stream().map(Purchase::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<Purchase> getUniquePurchasesByYear(List<Purchase> purchases, String year){
        Map<String, List<Purchase>> purchasesByYear = purchases.stream().collect(Collectors.groupingBy(Purchase::getYear));

        return purchasesByYear.get(year).stream().
                filter(purchase -> purchase.getItens().size() == 1)
                .collect(Collectors.toList());
    }


}

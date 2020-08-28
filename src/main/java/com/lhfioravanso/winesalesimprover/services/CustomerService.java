package com.lhfioravanso.winesalesimprover.services;

import com.lhfioravanso.winesalesimprover.dto.CustomerWithFidelityResponseDto;
import com.lhfioravanso.winesalesimprover.dto.CustomerWithHighestPurchaseByYearResponseDto;
import com.lhfioravanso.winesalesimprover.dto.CustomerWithTotalPurchaseValueResponseDto;
import com.lhfioravanso.winesalesimprover.dto.WineRecommendationResponseDto;

import java.util.List;

public interface CustomerService {
    List<CustomerWithTotalPurchaseValueResponseDto> listCustomersByHighestTotalPurchaseValues();
    CustomerWithHighestPurchaseByYearResponseDto getCustomerWithHighestPurchaseByYear(String year);
    List<CustomerWithFidelityResponseDto> listCustomersByFidelity();
    WineRecommendationResponseDto wineRecommendation(int customerId);
}

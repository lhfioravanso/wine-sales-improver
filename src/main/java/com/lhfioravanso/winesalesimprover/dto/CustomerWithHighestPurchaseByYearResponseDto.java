package com.lhfioravanso.winesalesimprover.dto;

import java.math.BigDecimal;

public class CustomerWithHighestPurchaseByYearResponseDto extends CustomerResponseDto {

    private BigDecimal highestPurchaseValue;

    public CustomerWithHighestPurchaseByYearResponseDto(int id, String name, String cpf, BigDecimal highestPurchaseValue) {
        super(id, name, cpf);
        this.highestPurchaseValue = highestPurchaseValue;
    }

    public BigDecimal getHighestPurchaseValue() {
        return highestPurchaseValue;
    }

    public void setHighestPurchaseValue(BigDecimal highestPurchaseValue) {
        this.highestPurchaseValue = highestPurchaseValue;
    }
}

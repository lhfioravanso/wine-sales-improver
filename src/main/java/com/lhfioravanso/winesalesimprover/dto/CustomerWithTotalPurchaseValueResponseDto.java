package com.lhfioravanso.winesalesimprover.dto;

import java.math.BigDecimal;

public class CustomerWithTotalPurchaseValueResponseDto extends CustomerResponseDto {

    public CustomerWithTotalPurchaseValueResponseDto(int id, String name, String cpf, BigDecimal totalPurchaseValue) {
        super(id, name, cpf);
        this.totalPurchaseValue = totalPurchaseValue;
    }

    private BigDecimal totalPurchaseValue;

    public BigDecimal getTotalPurchaseValue() {
        return totalPurchaseValue;
    }

    public void setTotalPurchaseValue(BigDecimal totalPurchaseValue) {
        this.totalPurchaseValue = totalPurchaseValue;
    }
}

package com.lhfioravanso.winesalesimprover.dto;

public class CustomerWithFidelityResponseDto extends CustomerResponseDto {

    public CustomerWithFidelityResponseDto(int id, String name, String cpf, int amountOfPurchases) {
        super(id, name, cpf);
        this.amountOfPurchases = amountOfPurchases;
    }

    private int amountOfPurchases;

    public int getAmountOfPurchases() {
        return amountOfPurchases;
    }

    public void setAmountOfPurchases(int amountOfPurchases) {
        this.amountOfPurchases = amountOfPurchases;
    }
}

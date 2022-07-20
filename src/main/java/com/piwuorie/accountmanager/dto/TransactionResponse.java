package com.piwuorie.accountmanager.dto;

import lombok.Data;

@Data
public class TransactionResponse {
    Double amount;
    Double availableBalance;
    String message;

    public TransactionResponse(Double amountDeposited, Double availableBalance, String message) {
        this.amount = amountDeposited;
        this.availableBalance = availableBalance;
        this.message = message;
    }

}

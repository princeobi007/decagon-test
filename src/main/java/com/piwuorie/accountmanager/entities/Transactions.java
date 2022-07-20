package com.piwuorie.accountmanager.entities;


import com.piwuorie.accountmanager.enums.TransactionTypeEnum;

public class Transactions {
    private Long id;
    private String accountNumber;
    private TransactionTypeEnum transactionType;
    private Double amount;

    public Transactions(Long id, String accountNumber, TransactionTypeEnum transactionType, Double amount) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public TransactionTypeEnum getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionTypeEnum transactionType) {
        this.transactionType = transactionType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}

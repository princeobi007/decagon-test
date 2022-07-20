package com.piwuorie.accountmanager.entities;


import com.piwuorie.accountmanager.enums.TransactionTypeEnum;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Data
public class Account {
    private Long id;
    private String accountName;
    private String accountNumber;
    private Double availableBalance;
    private LocalDateTime createdAt;
    private final List<Transactions> transactionsList = Collections.synchronizedList(new ArrayList<>());


    public Account(Long id, String accountName, String accountNumber) {
        this.id = id;
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.createdAt = LocalDateTime.now();
    }

    public synchronized Double getAvailableBalance() {
        Double balance = 0.0;
        for(Transactions transactions: transactionsList){
            if(transactions.getTransactionType().equals(TransactionTypeEnum.DEPOSIT)){
                balance += transactions.getAmount();
            }else{
                balance -= transactions.getAmount();
            }
        }
        return balance;
    }
}
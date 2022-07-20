package com.piwuorie.accountmanager.service;


import com.piwuorie.accountmanager.dto.TransactionResponse;
import com.piwuorie.accountmanager.entities.Account;
import com.piwuorie.accountmanager.entities.Transactions;
import com.piwuorie.accountmanager.enums.TransactionTypeEnum;
import com.piwuorie.accountmanager.usecase.IAccountInterface;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@Service
public class AccountService implements IAccountInterface {
    private final Map<String, Account> accountEntityList = Collections.synchronizedMap(new HashMap<>());
    private static final String padding = "0000000000";

    public synchronized String createAccount(String name, String phoneNumber) {
        String accountNumber = generateAccountNumber();
        Account accountEntity = new Account((long) accountEntityList.size(), name, accountNumber);
        accountEntityList.put(accountNumber, accountEntity);
        return accountEntity.getAccountNumber();
    }

    public synchronized TransactionResponse deposit(Double amount, final String accountNumber) {
        Account requiredAccount = accountEntityList.get(accountNumber);
        if (requiredAccount == null) {
            return new TransactionResponse(amount, 0.00, "could not find account");
        }

        Transactions transaction = new Transactions((long) requiredAccount.getTransactionsList().size(), accountNumber,
                TransactionTypeEnum.DEPOSIT, amount);

        requiredAccount.getTransactionsList().add(transaction);
        return new TransactionResponse(amount, requiredAccount.getAvailableBalance(), "Success");
    }

    public synchronized TransactionResponse withdraw(Double amount, String accountNumber) {

        Account requiredAccount = accountEntityList.get(accountNumber);
        if (requiredAccount == null) {
            return new TransactionResponse(amount, 0.00, "could not find account");
        }

        Transactions transaction = new Transactions((long) requiredAccount.getTransactionsList().size(), accountNumber,
                TransactionTypeEnum.WITHDRAWAL, amount);

        if (amount < requiredAccount.getAvailableBalance()) {
            return new TransactionResponse(amount, requiredAccount.getAvailableBalance(), "INSUFFICIENT BALANCE");
        }

        requiredAccount.getTransactionsList().add(transaction);
        return new TransactionResponse(amount, requiredAccount.getAvailableBalance(), "Success");
    }

    @Override
    public Map<String, Account> getAllAccount() {
        return this.accountEntityList;
    }

    public String generateAccountNumber() {
        return leftPad("" + accountEntityList.size());
    }

    private String leftPad(String str) {
        if (str.length() < padding.length()) {
            return padding.substring(str.length()) + str;
        }
        return str;
    }
}

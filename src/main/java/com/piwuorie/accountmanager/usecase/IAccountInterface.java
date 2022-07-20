package com.piwuorie.accountmanager.usecase;


import com.piwuorie.accountmanager.dto.TransactionResponse;
import com.piwuorie.accountmanager.entities.Account;

import java.util.Map;

public interface IAccountInterface {

    String createAccount(String name, String phoneNumber);

    TransactionResponse deposit(Double amount, String accountNumber);

    TransactionResponse withdraw(Double amount, String accountNumber);

    Map<String, Account> getAllAccount();
}

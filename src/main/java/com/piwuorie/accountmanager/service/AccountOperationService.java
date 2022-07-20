package com.piwuorie.accountmanager.service;


import com.piwuorie.accountmanager.dto.TransactionResponse;
import com.piwuorie.accountmanager.usecase.IAccountInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountOperationService {
    private final IAccountInterface iAccountInterface;


    public List<String> createAccounts() {
        List<String> accountNumbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    accountNumbers.add(iAccountInterface.createAccount(generateRandomString(), generateRandomString()));
                }
            }).start();
        }
        return accountNumbers;
    }

    public List<TransactionResponse> deposit(String accountNumber, Double amount) {
        List<TransactionResponse> transactionResponseList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    transactionResponseList.add(iAccountInterface.deposit(generateRandomDouble(), accountNumber));
                }
            }).start();

        }
        return transactionResponseList;
    }

    public List<TransactionResponse> withdraw(String accountNumber, Double amount) {
        List<TransactionResponse> transactionResponseList = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    transactionResponseList.add(iAccountInterface.withdraw(generateRandomDouble(), accountNumber));
                }
            }).start();

        }
        return transactionResponseList;
    }

    private double generateRandomDouble() {
        double leftLimit = 1D;
        double rightLimit = 10D;
        return new BigDecimal(leftLimit + new Random().nextDouble() * (rightLimit - leftLimit),
                new MathContext(2, RoundingMode.HALF_UP)).doubleValue();
    }

    private String generateRandomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

    }

}

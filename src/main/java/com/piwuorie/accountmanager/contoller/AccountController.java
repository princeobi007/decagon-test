package com.piwuorie.accountmanager.contoller;

import com.piwuorie.accountmanager.service.AccountOperationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api")
@RequiredArgsConstructor

public class AccountController{

    private final AccountOperationService accountOperationService;


    @GetMapping(value = "create-accounts")
    public ResponseEntity<?> createAccount(){
        return ResponseEntity.ok().body(accountOperationService.createAccounts());
    }


    @GetMapping(value = "do-deposit")
    public ResponseEntity<?> doDeposit(@RequestParam(value = "amount")Double amount,
                                       @RequestParam(value = "accountNumber") String accountNumber){
        return ResponseEntity.ok().body(accountOperationService.deposit(accountNumber,amount));
    }

    @GetMapping(value = "do-withdrawal")
    public ResponseEntity<?> doWithdrawal(@RequestParam(value = "amount")Double amount,
                                       @RequestParam(value = "accountNumber") String accountNumber){
        return ResponseEntity.ok().body(accountOperationService.withdraw(accountNumber,amount));
    }

}

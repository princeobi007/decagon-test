package com.piwuorie.accountmanager;

import com.piwuorie.accountmanager.dto.TransactionResponse;
import com.piwuorie.accountmanager.entities.Account;
import com.piwuorie.accountmanager.entities.Transactions;
import com.piwuorie.accountmanager.usecase.IAccountInterface;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.Map;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountManagerApplicationTests {

	@Autowired
	IAccountInterface iAccountInterface;

	@Test
	void contextLoads() {
	}

	@Order(1)
	@Test
	@DisplayName("Create Account")
	public void createAccounts(){
		int totalAccounts = 10;

		CreateAccountRunnable runnable = new CreateAccountRunnable();
		for (int i =0 ; i < totalAccounts; i++){
			log.info("Staring thread : {}",i);
			Thread thread = new Thread(runnable, "Thread "+i);
			thread.start();
		}
	}

	@Order(2)
	@Test
	@DisplayName("Do deposit")
	public void doDeposit(){
		int totalAccounts = 10;

		DoDepositRunnable runnable = new DoDepositRunnable();
		for (int i =0 ; i < totalAccounts; i++){
			log.info("Staring thread : {}",i);
			Thread thread = new Thread(runnable, "Thread "+i);
			thread.start();
		}
	}

	@Order(3)
	@Test
	@DisplayName("Do Withdrawal")
	public void doWithdrawal(){
		int totalAccounts = 10;

		WithdrawalRunnable runnable = new WithdrawalRunnable();
		for (int i =0 ; i < totalAccounts; i++){
			log.info("Staring thread : {}",i);
			Thread thread = new Thread(runnable, "Thread "+i);
			thread.start();
		}
	}

	class CreateAccountRunnable  implements Runnable{

		@Override
		public void run() {
			String accountNumber = iAccountInterface.createAccount("demo user ",""+System.currentTimeMillis());
			System.out.println("Account created: "+ accountNumber);
		}
	}

	class DoDepositRunnable implements Runnable{
		@Override
		public  void run(){
			Map<String, Account> accountMap = iAccountInterface.getAllAccount();
			accountMap.forEach((key, value) -> {
				TransactionResponse transactionResponse = iAccountInterface.deposit(40.00, (String) key);
				System.out.println("Deposit completed: "+ transactionResponse.toString());
			});

		}
	}

	class WithdrawalRunnable implements Runnable{
		@Override
		public  void run(){
			Map<String, Account> accountMap = iAccountInterface.getAllAccount();
			accountMap.forEach((key, value) -> {
				TransactionResponse transactionResponse = iAccountInterface.withdraw(10.00, (String) key);
				System.out.println("Withdrawal complete: "+ transactionResponse.toString());
			});

		}
	}
}

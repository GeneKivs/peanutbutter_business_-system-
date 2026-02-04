package com.peanutbutter.peanutbutter.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peanutbutter.peanutbutter.model.Account;

import com.peanutbutter.peanutbutter.model.enums.AccountType;
import com.peanutbutter.peanutbutter.repository.AccountRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

   
    String accounttobeadded;

    public void defineAccount(String accountName, String accountType, String accountCategory, HttpServletRequest request) {
        Account account = new Account();
        account.setAccountName(accountName);
        account.setAccountType(AccountType.valueOf(accountType));
        account.setAccountCategory(accountCategory);
        account.setBalance(BigDecimal.ZERO);//initialize balance to zero
        accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public void addToExpenseAccountBalance(String expenceName, BigDecimal amount) {
        Account account = accountRepository.findByAccountNameAndAccountCategory(expenceName, "EXPENSE");
        if (account != null) {
            BigDecimal currentBalance = account.getBalance() != null ? account.getBalance() : BigDecimal.ZERO;
            account.setBalance(currentBalance.add(amount));
            accountRepository.save(account);
        } else {
            // Create new expense account if it doesn't exist
            Account newAccount = new Account();
            newAccount.setAccountName(expenceName);
            newAccount.setAccountType(AccountType.EXPENSE);
            newAccount.setAccountCategory("EXPENSE");
            newAccount.setBalance(amount);
            accountRepository.save(newAccount);
        }
        
    }

    public void reduceAccountBalance(String accountName, BigDecimal amount) {
    Account account = accountRepository.findByAccountName(accountName);
    if (account != null) {
        BigDecimal currentBalance = account.getBalance() != null ? account.getBalance() : BigDecimal.ZERO;
        account.setBalance(currentBalance.subtract(amount));
        accountRepository.save(account);
    }

    
}
public void addAccountBalance(String paymentName, BigDecimal amount){
    String accounttobeadded = null;
    if ("M-pesa".equals(paymentName)) {
        accounttobeadded = "KCB Mpesa";
    } else if ("Bank".equals(paymentName)) {
        accounttobeadded = "KCB Bank";
    } else if ("Cash".equals(paymentName)) {
        accounttobeadded = "Cash Account";
    } else if ("debt".equals(paymentName)) {
        accounttobeadded = "Debt Account";
    }
    else if ("credit".equals(paymentName)) {
        accounttobeadded = "Credit Account";
    }
    // Add more payment types as needed

    if (accounttobeadded != null) {
        Account account = accountRepository.findByAccountName(accounttobeadded);
        if (account != null) {
            BigDecimal currentBalance = account.getBalance() != null ? account.getBalance() : BigDecimal.ZERO;
            account.setBalance(currentBalance.add(amount));
            accountRepository.save(account);
        } else {
            // Create the account if it doesn't exist
            Account newAccount = new Account();
            newAccount.setAccountName(accounttobeadded);
            newAccount.setAccountType(AccountType.ASSET); // or appropriate type
            newAccount.setAccountCategory("BANK");
            newAccount.setBalance(amount);
            accountRepository.save(newAccount);
        }
    }
}
// Add or update a customer account for debt (balance)
    public void addOrCreateCustomerAccountBalance(String customerAccountName, BigDecimal amount) {
        // Try to find an account with the customer's name and category CUSTOMER
        Account account = accountRepository.findByAccountNameAndAccountCategory(customerAccountName, "CUSTOMER");
        if (account != null) {
            BigDecimal currentBalance = account.getBalance() != null ? account.getBalance() : BigDecimal.ZERO;
            account.setBalance(currentBalance.add(amount));
            accountRepository.save(account);
        } else {
            // Create new customer account if it doesn't exist
            Account newAccount = new Account();
            newAccount.setAccountName(customerAccountName);
            newAccount.setAccountType(AccountType.ASSET); // or a specific type for customer
            newAccount.setAccountCategory("CUSTOMER");
            newAccount.setBalance(amount);
            accountRepository.save(newAccount);
        }
    }

    public void addToSalesAccount(BigDecimal amount) {
        // Find or create the Sales Account (category: INCOME, name: Sales Account)
        Account account = accountRepository.findByAccountNameAndAccountCategory("Sales Account", "INCOME");
        if (account != null) {
            BigDecimal currentBalance = account.getBalance() != null ? account.getBalance() : BigDecimal.ZERO;
            account.setBalance(currentBalance.add(amount));
            accountRepository.save(account);
        } else {
            Account newAccount = new Account();
            newAccount.setAccountName("Sales Account");
            newAccount.setAccountType(AccountType.REVENUE); // Use REVENUE as per enum
            newAccount.setAccountCategory("INCOME");
            newAccount.setBalance(amount);
            accountRepository.save(newAccount);
        }
    }

    public void makeDebtPayment(String accountName, String paymentName, BigDecimal amount){
        Account account = accountRepository.findByAccountNameAndAccountCategory(accountName,"CUSTOMER" );
        if (account != null) {
            BigDecimal currentCuatomerBalance = account.getBalance() != null ? account.getBalance() : BigDecimal.ZERO;
           account.setBalance(currentCuatomerBalance.subtract(amount));
           accountRepository.save(account);        }
        
        
        reduceAccountBalance(accountName, amount);
        addAccountBalance(paymentName, amount);
       
    
    }

    public Optional<Account> findAccountByID(int accountID) {
        return accountRepository.findById(accountID);
    }
}

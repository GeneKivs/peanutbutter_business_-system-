package com.peanutbutter.peanutbutter.model;

import java.math.BigDecimal;

import com.peanutbutter.peanutbutter.model.enums.AccountType;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountID;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "account_type")
     @Enumerated(EnumType.STRING)
    private AccountType accountType;

    
   @Column(precision = 10, scale = 2)
    private BigDecimal balance;

    @Column(name = "account_category")
    private String accountCategory; // e.g., BANK, EXPENSE, CUSTOMER, etc.

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getAccountCategory() {
        return accountCategory;
    }

    public void setAccountCategory(String accountCategory) {
        this.accountCategory = accountCategory;
    }



}

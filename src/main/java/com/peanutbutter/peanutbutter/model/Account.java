package com.peanutbutter.peanutbutter.model;


import com.peanutbutter.peanutbutter.base.Auditable;
import com.peanutbutter.peanutbutter.model.enums.AccountType;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Account extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountID;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "account_type")
     @Enumerated(EnumType.STRING)
    private AccountType accountType;

    
   
    public Long getAccountID() {
        return accountID;
    }

    public void setAccountID(Long accountID) {
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

   

   



}

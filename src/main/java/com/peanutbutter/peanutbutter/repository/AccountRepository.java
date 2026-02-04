package com.peanutbutter.peanutbutter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.peanutbutter.peanutbutter.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findByAccountNameAndAccountCategory(String accountName, String accountCategory);

    @Query(value = "select * from accounts where account_category = 'BANK'  AND account_type = 'ASSET'",nativeQuery = true)
    List<Account> findAllBankaccounts();

    @Query(value = "select * from accounts where account_category = 'CUSTOMER'", nativeQuery = true)
    List<Account> findcustomerAccounts();

    Account  findByAccountName(String accountName);

}

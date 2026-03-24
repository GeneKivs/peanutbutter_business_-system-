package com.peanutbutter.peanutbutter.mapper;

import com.peanutbutter.peanutbutter.dtos.AccountRequestDto;
import com.peanutbutter.peanutbutter.dtos.AccountResponseDto;
import com.peanutbutter.peanutbutter.model.Account;

public class AccountMapper {

    private AccountMapper(){}

    public static Account toEntity(AccountRequestDto dto){

        Account account = new Account();
        account.setAccountName(dto.getAccountName());

        return account;
    }

    public static AccountResponseDto toResponse(Account account){
        AccountResponseDto dto = new AccountResponseDto();

        dto.setAccountID(account.getAccountID());
        dto.setAccountName(account.getAccountName());
        dto.setCreatedAT(account.getCreatedAt());
        dto.setUpdatedAt(account.getUpdatedAt());

        return dto;
    }

    

}

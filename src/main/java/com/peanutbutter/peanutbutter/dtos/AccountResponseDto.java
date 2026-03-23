package com.peanutbutter.peanutbutter.dtos;

import java.time.LocalDate;

public class AccountResponseDto {

    private Long accountID;
    private String accountName;
    private String accountType;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public Long getAccountID(){
        return accountID;
    }

    public void setAccountID(Long id){
        this.accountID = id;
    }

    public String getAccountName(){
        return accountName;
    }

    public void setAccountName(String name){
        this.accountName = name;
    }

    public String getAccountType(){
        return accountType;
    }
    public void setAccountType(String type){
        this.accountType = type;
    }

    public LocalDate getCreatedAt(){
        return createdAt;
    }

    public void setCreatedAT(LocalDate date){
        this.createdAt = date;
    }

    public LocalDate getUpdatedAT(){
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate date){
        this.updatedAt = date;
    }

}

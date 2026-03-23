package com.peanutbutter.peanutbutter.dtos;

public class AccountRequestDto {

    private String accountName;
    private String accountType;

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

}

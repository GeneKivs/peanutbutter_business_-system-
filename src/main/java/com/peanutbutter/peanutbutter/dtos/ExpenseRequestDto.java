package com.peanutbutter.peanutbutter.dtos;

public class ExpenseRequestDto {

    private String expemseName;
    private String description;

    public String getExpenseName(){
        return expemseName;
    }

    public void setExpenseName(String name){
        this.expemseName = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }


}

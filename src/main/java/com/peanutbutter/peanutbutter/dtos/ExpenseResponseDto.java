package com.peanutbutter.peanutbutter.dtos;

import java.time.LocalDate;

public class ExpenseResponseDto {

    private int expenseID;
    private String expenseName;
    private String description;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public int getExpenseID(){
        return expenseID;
    }

    public void setExpenseID(int id){
        this.expenseID = id;
    }

    public String getExpenseName(){
        return expenseName;
    }

    public void setEspenseName(String name){
        this.expenseName = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescrption(String description){
        this.description = description;
    }

    public LocalDate getCreatedAt(){
        return createdAt;
    }

    public void setCreatedAT(LocalDate date){
        this.createdAt = date;
    }


    public LocalDate getUpdatedAt(){
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate date){
        this.updatedAt = date;
    }

}

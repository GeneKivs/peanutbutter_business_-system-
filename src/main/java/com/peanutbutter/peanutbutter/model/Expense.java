package com.peanutbutter.peanutbutter.model;

import com.peanutbutter.peanutbutter.base.Auditable;

import jakarta.persistence.*;



@Entity
@Table(name = "expenses")
public class Expense extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int expenseID;

    private String expenseName;

    private String description;

    public int getExpenseID(){
        return expenseID;
    }

    public void setExpenseID(int expenseID){
        this.expenseID = expenseID;
    }

    public String getExpenseName(){
        return expenseName;
    }

    public void setExpenseName(String expenseName){
        this.expenseName = expenseName;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }
}

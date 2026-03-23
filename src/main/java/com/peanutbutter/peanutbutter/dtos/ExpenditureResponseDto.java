package com.peanutbutter.peanutbutter.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpenditureResponseDto {

    private Long expenditureID;
    private Long expenseID;
    private String expenseName;
    private Long paymentMethodID;
    private String paymentType;
    private BigDecimal amountSpent;
    private LocalDate expenditureDate;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public Long getExpenditureID(){
        return expenditureID;
    }

    public void setExpenditureID(Long id){
        this.expenditureID = id;
    }

    public Long getExpenseID(){
        return expenseID;
    }

    public void setExpenseID(Long id){
        this.expenseID = id;
    }

    public String getExpenseName(){
        return expenseName;
    }

    public void setExpenseName(String name){
        this.expenseName = name;
    }

    public Long getPaymentMethodID(){
        return paymentMethodID;
    }

    public void stePaymentMethod(Long id){
        this.paymentMethodID = id;
    }

    public String getPaymentType(){
        return paymentType;
    }

    public void setPaymentType(String type){
        this.paymentType = type;
    }

    public BigDecimal getAmountSpent(){
        return amountSpent;
    }

    public void setAmountSpent(BigDecimal amount){
        this.amountSpent = amount;
    }


    public LocalDate getExpenditureDate(){
        return expenditureDate;
    }

    public void setExpenditureDate(LocalDate date){
        this.expenditureDate = date;
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

    public void setUpdateAt(LocalDate date){
        this.updatedAt = date;
    }

}

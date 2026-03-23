package com.peanutbutter.peanutbutter.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpenditureRequestDto {

    private Long expenseid;
    private LocalDate expenditureDate;
    private BigDecimal amountSpent;
    private Long paymentMethodID;

    public Long getExpenseID(){
        return expenseid;
    }

    public void setExpenseID(Long id){
        this.expenseid = id;
    }

    public LocalDate getExpenditureDate(){
        return expenditureDate;
    }

    public void setExpenditureDate(LocalDate date){
        this.expenditureDate = date;
    }

    public BigDecimal getAmountSpent(){
        return amountSpent;
    }

    public void setAmountSpent(BigDecimal amount){
        this.amountSpent = amount;
    }

    public Long getPaymentMethod(){
        return paymentMethodID;
    }

    public void setPaymentMethod(Long id){
        this.paymentMethodID = id;
    }

}

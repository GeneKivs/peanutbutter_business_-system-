package com.peanutbutter.peanutbutter.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BatchRequestDto {

    private LocalDate receivedDate;
    private int peanutQuantity;
    private BigDecimal amountPaid;

    public LocalDate getReceivedDate(){
        return receivedDate;
    }

    public void setReceivedDate(LocalDate date){
        this.receivedDate = date;
    }

    public int getPeanutQuantity(){
        return peanutQuantity;
    }

    public void setPeanutQuantity(int quantity ){
        this.peanutQuantity = quantity;
    }

    public BigDecimal getAmountPaid(){
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amount){
        this.amountPaid = amount;
    }

}

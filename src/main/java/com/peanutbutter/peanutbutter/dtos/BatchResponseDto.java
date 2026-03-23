package com.peanutbutter.peanutbutter.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BatchResponseDto {

    private Long batchID;
    private LocalDate receivedDate;
    private int peanutQuantity;
    private BigDecimal  amountPaid;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public Long getBatchID(){
        return batchID;
    }

    public void setBatchID(Long id){
        this.batchID = id;
    }

    public LocalDate getReceivedDate(){
        return receivedDate;
    }

    public void setReceivedDate(LocalDate date){
        this.receivedDate = date;
    }

    public int getPeanutQuantity(){
    return peanutQuantity;
    }

    public void setPeanutQuantity(int quantity){
        this.peanutQuantity = quantity;
    }

    public BigDecimal getAmountPaid(){
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amount){
        this.amountPaid = amount;
    }

    public LocalDate getCreatedAt(){
        return createdAt;
    }

    public void setCreatedAt(LocalDate date){
        this.createdAt = date;
    }

    public LocalDate getUpdatedAt(){
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate date){
        this.updatedAt = date;
    }

}

package com.peanutbutter.peanutbutter.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.peanutbutter.peanutbutter.base.Auditable;


import jakarta.persistence.*;

@Entity
@Table(name = "batch")
public class Batch extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long batchID;

    private LocalDate receivedDate;

    private int peanutQuantity;

    @Column(precision = 10, scale = 2)
    private BigDecimal amountPaid;


 
    public Long getBatchID(){
        return batchID;
    }

    public void setBatchID(Long batchid){
        this.batchID = batchid;
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

    public void setPeanutQuantity(int peanutQuantity){
        this.peanutQuantity =peanutQuantity;
    }

    public BigDecimal getAmountPaid(){
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid){
        this.amountPaid = amountPaid;
    }


}

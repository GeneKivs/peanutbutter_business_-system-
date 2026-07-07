package com.peanutbutter.peanutbutter.model;


import java.time.LocalDate;

import com.peanutbutter.peanutbutter.base.Auditable;


import jakarta.persistence.*;

//its like grn for receiving purchased goods since its done against the purchase order
@Entity
@Table(name = "batch")
public class Batch extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long batchID;

    private LocalDate receivedDate;

    @ManyToOne
    @JoinColumn(name = "purchaseid")
   private Purchase purchase;


 
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

   

    public Purchase getPurchase(){
        return purchase;
    }

    public void setPurchase(Purchase purchase){
        this.purchase =purchase;
    }

   


}

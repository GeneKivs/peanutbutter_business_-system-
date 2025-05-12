package com.peanutbutter.peanutbutter.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "batch")
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int batchID;
    private LocalDate receivedDate;
    private int totalTins;
    private int peanutQuantity;
    private double amountPaid;
    private int RemainingQuantity;

    public int getRemainingQuantity() {
        return RemainingQuantity;
    }

    public void setRemainingQuantity(int remainingQuantity) {
        RemainingQuantity = remainingQuantity;
    }

    public int getBatchID(){
        return batchID;
    }

    public void setBatchID(int batchid){
        this.batchID = batchid;
    }

    public LocalDate getReceivedDate(){
        return receivedDate;
    }

    public void setReceivedDate(LocalDate date){
        this.receivedDate = date;
    }

    public int getTotalTins(){
        return totalTins;
    }

    public void setTotalTins(int totalTins){
        this.totalTins= totalTins;
    }

    public int getPeanutQuantity(){
        return peanutQuantity;
    }

    public void setPeanutQuantity(int peanutQuantity){
        this.peanutQuantity =peanutQuantity;
    }

    public double getAmountPaid(){
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid){
        this.amountPaid = amountPaid;
    }


}

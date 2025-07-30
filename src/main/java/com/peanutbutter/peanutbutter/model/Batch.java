package com.peanutbutter.peanutbutter.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.peanutbutter.peanutbutter.model.enums.BatchStatus;

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
    @Column(precision = 10, scale = 2)
    private BigDecimal amountPaid;
    private int RemainingQuantity;

    @Column(precision = 10, scale = 2)
    private BigDecimal revenue;
    
   @Enumerated(EnumType.STRING)
    private BatchStatus batchStatus;
    @Column(precision = 10, scale = 2)
    private BigDecimal profit;
   
    private LocalDate endDate;

     public BatchStatus getBatchStatus() {
    return batchStatus;
}
   public void setBatchStatus(BatchStatus batchStatus) {
    this.batchStatus = batchStatus;
   }

    

    public LocalDate getEndDate() {
        return endDate; 
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

     public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit ){
        this.profit = profit;
    }


 public BigDecimal getRevenue() {
        return revenue;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }
    

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

    public BigDecimal getAmountPaid(){
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid){
        this.amountPaid = amountPaid;
    }


}

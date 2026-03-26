package com.peanutbutter.peanutbutter.dtos;


import java.time.LocalDate;

public class BatchResponseDto {

    private Long batchID;
    private LocalDate receivedDate;
    private Long purchaseID;
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

    public Long getPurchaseID(){
        return purchaseID;
    }

    public void setPurchaseID(Long id){
        this.purchaseID = id;
    }

    

}

package com.peanutbutter.peanutbutter.dtos;

import java.time.LocalDate;

public class BatchRequestDto {

    private LocalDate receivedDate;
    private Long purchaseID;

    public LocalDate getReceivedDate(){
        return receivedDate;
    }

    public void setReceivedDate(LocalDate date){
        this.receivedDate = date;
    }

     public Long getPurchaseID(){
        return purchaseID;
    }

    public void setPurchaseID(Long id){
        this.purchaseID = id;
    }

    

}

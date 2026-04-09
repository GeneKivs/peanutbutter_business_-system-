package com.peanutbutter.peanutbutter.dtos;

import java.time.LocalDate;
import java.util.List;

public class BatchRequestDto {

    private LocalDate receivedDate;
    private Long purchaseID;
    private List<BatchProductRequestDto> products;

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

    public List<BatchProductRequestDto> getProducts(){
        return products;
    }

    

    

}

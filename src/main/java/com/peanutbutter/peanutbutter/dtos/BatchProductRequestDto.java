package com.peanutbutter.peanutbutter.dtos;



public class BatchProductRequestDto {

    private Long batchID;
    private int productID;
    private int productQuantity;

    public Long getBatchID(){
        return batchID;
    }

    public void setBatchID(Long  id){
        this.batchID = id;
    }

    public int getProductId(){
        return productID;
    }

    public void setProductID(int id){
        this.productID = id;
    }

    public int getProductQuantity(){
        return productQuantity;
    }

    public void setProductQuantity(int quantity){
        this.productQuantity = quantity;
    }

}

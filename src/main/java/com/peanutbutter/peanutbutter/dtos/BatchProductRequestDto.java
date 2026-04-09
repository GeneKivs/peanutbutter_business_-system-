package com.peanutbutter.peanutbutter.dtos;



public class BatchProductRequestDto {

    private Long productID;
    private int productQuantity;
   

    

    public Long getProductId(){
        return productID;
    }

    public void setProductID(Long id){
        this.productID = id;
    }

    public int getProductQuantity(){
        return productQuantity;
    }

    public void setProductQuantity(int quantity){
        this.productQuantity = quantity;
    }

    

}

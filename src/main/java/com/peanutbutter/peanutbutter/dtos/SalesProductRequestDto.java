package com.peanutbutter.peanutbutter.dtos;

import java.math.BigDecimal;

public class SalesProductRequestDto {

    private Long salesID;
    private Long batchID;
    private Long productID;
    private int quantity;
    private BigDecimal sellingPrice;
    public Long getSalesID() {
        return salesID;
    }

   public void setSalesID(Long salesID) {
        this.salesID = salesID;
    }

    public Long getBatchID() {
        return batchID;
    }

    public void setBatchID(Long batchID) {
        this.batchID = batchID;
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    } 



}

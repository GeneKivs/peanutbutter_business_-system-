package com.peanutbutter.peanutbutter.dtos;

public class SalesProductResponseDto {

    private Long salesProductID;
    private Long salesID;
    private Long batchID;
    private Long productID;
    private int quantity;
    private Double sellingPrice;

    public Long getSalesProductID() {
        return salesProductID;
    }

    public void setSalesProductID(Long salesProductID) {
        this.salesProductID = salesProductID;
    }

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

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

}

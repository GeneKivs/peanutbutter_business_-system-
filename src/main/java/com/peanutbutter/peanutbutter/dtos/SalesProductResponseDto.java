package com.peanutbutter.peanutbutter.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SalesProductResponseDto {

    private Long salesProductID;
    private Long salesID;
    private Long productID;
    private int quantity;
    private BigDecimal sellingPrice;
    private BigDecimal total;
    private LocalDate createdAt;
    private LocalDate updatedAT;

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

    public BigDecimal getTotal(){
        return total;
    }

    public void setTotal(BigDecimal total){
        this.total = total;
    }

    public LocalDate getCreatedAT(){
        return createdAt;
    }

    public void setCreatedAT(LocalDate date){
        this.createdAt = date;
    }

    public LocalDate getUpdatedAt(){
        return updatedAT;
    }

    public void setUpdatedAt(LocalDate date){
        this.updatedAT = date;
    }

}

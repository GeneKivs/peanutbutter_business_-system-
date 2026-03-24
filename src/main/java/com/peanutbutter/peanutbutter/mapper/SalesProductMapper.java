package com.peanutbutter.peanutbutter.mapper;

import com.peanutbutter.peanutbutter.dtos.SalesProductResponseDto;
import com.peanutbutter.peanutbutter.model.SalesProduct;

public class SalesProductMapper {

    private SalesProductMapper(){}

    public static SalesProductResponseDto toResponseDto(SalesProduct salesProduct){
        SalesProductResponseDto dto = new SalesProductResponseDto();

        dto.setSalesProductID(salesProduct.getSalesProductID());
        dto.setQuantity(salesProduct.getQuantity());
        if (salesProduct.getBatch() != null) {
            dto.setBatchID(salesProduct.getBatch().getBatchID());
        }
        if (salesProduct.getProduct() != null) {
            dto.setProductID(salesProduct.getProduct().getProductID());
        }

        if (salesProduct.getSales() != null) {
            dto.setSalesID(salesProduct.getSales().getSalesid());
        }

        dto.setSellingPrice(salesProduct.getSellingPrice());
        dto.setCreatedAT(salesProduct.getCreatedAt());
        dto.setUpdatedAt(salesProduct.getUpdatedAt());

        return dto;
    }

}

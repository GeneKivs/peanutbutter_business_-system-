package com.peanutbutter.peanutbutter.mapper;

import com.peanutbutter.peanutbutter.dtos.BatchProductResponseDto;
import com.peanutbutter.peanutbutter.model.BatchProduct;

public class BatchProductMapper {

    private BatchProductMapper(){}

    public static BatchProductResponseDto toResponseDto(BatchProduct batchProduct){
        BatchProductResponseDto dto = new BatchProductResponseDto();

        dto.setBatchProductID(batchProduct.getBatch_productID());
        dto.setProductQuantity(batchProduct.getProductQuantity());
        if (batchProduct.getBatch() != null) {
            dto.setBatchID(batchProduct.getBatch().getBatchID());
        }
        if(batchProduct.getProduct() != null){
            dto.setProductID(batchProduct.getProduct().getProductID());
            dto.setProductName(batchProduct.getProduct().getProductName());
        }

        dto.setCostPerTin(batchProduct.getCostPerUnit());
        dto.setProductRemQuantity(batchProduct.getProductRemQuantity());
        dto.setCreatedAt(batchProduct.getCreatedAt());
        dto.setUpdatedAt(batchProduct.getUpdatedAt());

        return dto;
    }

}

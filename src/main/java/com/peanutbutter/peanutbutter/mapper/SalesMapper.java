package com.peanutbutter.peanutbutter.mapper;

import com.peanutbutter.peanutbutter.dtos.SalesResponseDto;
import com.peanutbutter.peanutbutter.model.Sales;

public class SalesMapper {

    private SalesMapper(){}

    public static SalesResponseDto toResponseDto(Sales sales){
        SalesResponseDto dto = new SalesResponseDto();

        dto.setSalesID(sales.getSalesid());
        dto.setSalesDate(sales.getSalesOrderDate());
        if (sales.getCustomer() != null) {
            dto.setCustomerID(sales.getCustomer().getCustomerID());
            dto.setCustomerName(sales.getCustomer().getCustomerName());
        }
        dto.setCreatedAT(sales.getCreatedAt());
        dto.setUpdatedAt(sales.getUpdatedAt());

        return  dto;
    }

}

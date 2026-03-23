package com.peanutbutter.peanutbutter.mapper;

import com.peanutbutter.peanutbutter.dtos.ProductRequestDto;
import com.peanutbutter.peanutbutter.dtos.ProductResponseDto;
import com.peanutbutter.peanutbutter.model.Product;

public class ProductMapper {

    private ProductMapper (){}

    public static Product toEntity(ProductRequestDto dto){
        if (dto == null) {
            return null;
        }

        Product product = new Product();
        product.setProductName(dto.getProductName());
        product.setReorderLevel(dto.getReorderLevel());
        product.setPricePerTin(dto.getPricePerTin());

        return product;
    }

    public static ProductResponseDto toResponse(Product product){
        if (product == null) {
            return null;
        }

        ProductResponseDto dto = new ProductResponseDto();
        dto.setProductID(product.getProductID());
        dto.setProductName(product.getProductName());
        dto.setPricePerTin(product.getPricePerTin());
        dto.setCreatedAt(product.getCreatedAt());
        dto.setUpdatedAt(product.getUpdatedAt());

        return dto;
    }

}

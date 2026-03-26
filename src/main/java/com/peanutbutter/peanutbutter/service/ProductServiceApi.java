package com.peanutbutter.peanutbutter.service;

import java.util.List;

import com.peanutbutter.peanutbutter.dtos.ProductRequestDto;
import com.peanutbutter.peanutbutter.dtos.ProductResponseDto;

public interface ProductServiceApi {

    ProductResponseDto createProduct(ProductRequestDto requestDto);
    ProductResponseDto getProductByID(Long productID);
    ProductResponseDto updateProduct(Long productID,ProductRequestDto requestDto);
    List<ProductResponseDto> getAllProducts();


}

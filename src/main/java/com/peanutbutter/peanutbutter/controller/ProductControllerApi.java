package com.peanutbutter.peanutbutter.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peanutbutter.peanutbutter.dtos.ProductRequestDto;
import com.peanutbutter.peanutbutter.dtos.ProductResponseDto;
import com.peanutbutter.peanutbutter.service.ProductServiceApi;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductControllerApi {

    private final ProductServiceApi productServiceApi;

    public ProductControllerApi (ProductServiceApi productServiceApi){
        this.productServiceApi = productServiceApi;
    }


    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto requestDto){
        ProductResponseDto responseDto = productServiceApi.createProduct(requestDto);

        return new ResponseEntity<>(responseDto,HttpStatus.CREATED);
    }

    @GetMapping("/{productID}")
    public ResponseEntity<ProductResponseDto> getProdcutByID(@PathVariable Long productID){
        ProductResponseDto responseDto = productServiceApi.getProductByID(productID);

        return responseDto != null ? ResponseEntity.ok(responseDto) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{productID}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long productID,@RequestBody ProductRequestDto requestDto ){
        ProductResponseDto responseDto = productServiceApi.updateProduct(productID, requestDto);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(){
        List<ProductResponseDto> responseDtos = productServiceApi.getAllProducts();
        return ResponseEntity.ok(responseDtos);
    }


}

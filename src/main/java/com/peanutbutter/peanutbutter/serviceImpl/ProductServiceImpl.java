package com.peanutbutter.peanutbutter.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.peanutbutter.peanutbutter.dtos.ProductRequestDto;
import com.peanutbutter.peanutbutter.dtos.ProductResponseDto;
import com.peanutbutter.peanutbutter.mapper.ProductMapper;
import com.peanutbutter.peanutbutter.model.Product;
import com.peanutbutter.peanutbutter.repository.ProductRepository;
import com.peanutbutter.peanutbutter.service.ProductServiceApi;

@Service
public class ProductServiceImpl implements ProductServiceApi {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto requestDto){
        Product product = ProductMapper.toEntity(requestDto);
        Product savedProduct = productRepository.save(product);

        return ProductMapper.toResponse(savedProduct);

    }

    @Override
    public ProductResponseDto getProductByID(Long productID){
        return productRepository.findById(productID)
               .map(ProductMapper :: toResponse)
               .orElseThrow(); 
    }

    @Override
    public ProductResponseDto updateProduct(Long productID,ProductRequestDto requestDto){
        Product existingProduct = productRepository.findById(productID).orElseThrow();

        if (requestDto.getProductName() != null) {
            existingProduct.setProductName(requestDto.getProductName());
        }

        if (requestDto.getPricePerTin() != null) {
            existingProduct.setPricePerTin(requestDto.getPricePerTin());
            
        }

        existingProduct.setReorderLevel(requestDto.getReorderLevel());

        Product updatedProduct = productRepository.save(existingProduct);

        return ProductMapper.toResponse(updatedProduct);

    }

    @Override
    public List<ProductResponseDto> getAllProducts(){
        return productRepository.findAll()
                .stream()
                .map(ProductMapper :: toResponse)
                .collect(Collectors.toList());
    }

}

package com.peanutbutter.peanutbutter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peanutbutter.peanutbutter.model.Product;
import com.peanutbutter.peanutbutter.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void defineProduct(Product product){
        product.setPricePerTin(0.0);
        product.setQuantityInStock(0);
        productRepository.save(product);
    }

    public List <Product> getAllProducts (){
        return productRepository.findAll();
    }
    
    public void updateProduct(Product product){
        productRepository.save(product);
    }

    public void deleteProductByID(int productID){
        productRepository.deleteById(productID);
    }
}

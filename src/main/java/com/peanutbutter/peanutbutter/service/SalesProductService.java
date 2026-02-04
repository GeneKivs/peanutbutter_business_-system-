package com.peanutbutter.peanutbutter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peanutbutter.peanutbutter.model.SalesProduct;
import com.peanutbutter.peanutbutter.repository.SalesProductRepository;

@Service
public class SalesProductService {

    @Autowired
    private SalesProductRepository salesProductRepository;

    public void defineSalesProduct(SalesProduct salesProduct){
        salesProductRepository.save(salesProduct);
    }

    public List<SalesProduct> getALLSalesProducts(){
        return salesProductRepository.findAll();
    }

}

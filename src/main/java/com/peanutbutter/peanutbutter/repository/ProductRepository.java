package com.peanutbutter.peanutbutter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peanutbutter.peanutbutter.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}

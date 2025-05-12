package com.peanutbutter.peanutbutter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peanutbutter.peanutbutter.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

}

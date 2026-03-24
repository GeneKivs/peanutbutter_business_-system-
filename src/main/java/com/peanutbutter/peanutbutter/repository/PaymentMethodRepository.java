package com.peanutbutter.peanutbutter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peanutbutter.peanutbutter.model.PaymentMethod;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod,Long>{

}

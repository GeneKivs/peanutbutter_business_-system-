package com.peanutbutter.peanutbutter.service;

import java.util.List;

import com.peanutbutter.peanutbutter.dtos.CustomerRequestDto;
import com.peanutbutter.peanutbutter.dtos.CustomerResponseDto;

public interface CustomerServiceApi {

    CustomerResponseDto createCustomer(CustomerRequestDto requestDto);
    CustomerResponseDto getCustomerByID(Long customerID);
    CustomerResponseDto updateCustomer(Long customerID,CustomerRequestDto requestDto);
    List<CustomerResponseDto> getAllCustomers();

}

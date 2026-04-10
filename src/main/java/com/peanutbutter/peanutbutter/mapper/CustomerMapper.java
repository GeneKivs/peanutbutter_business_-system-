package com.peanutbutter.peanutbutter.mapper;

import com.peanutbutter.peanutbutter.dtos.CustomerRequestDto;
import com.peanutbutter.peanutbutter.dtos.CustomerResponseDto;
import com.peanutbutter.peanutbutter.model.Customer;

public class CustomerMapper {

    private CustomerMapper(){}

    public static Customer toEntity(CustomerRequestDto dto){
        if (dto == null) {
            return null;
        }

        Customer customer = new Customer();
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setPhoneNumber(dto.getPhoneNumber());

        return customer;
    }

    public static CustomerResponseDto toResponse(Customer customer){
        if (customer == null) {
            return null;
        }

        CustomerResponseDto dto = new CustomerResponseDto();
        dto.setCustomerID(customer.getCustomerID());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        String fullName = customer.getFirstName() + " " + customer.getLastName();
        dto.setCustomerName(fullName);
        dto.setPhoneNumber(customer.getPhoneNumber());
        dto.setCreatedAT(customer.getCreatedAt());
        dto.setUpdatedAt(customer.getUpdatedAt());
        
        
        return dto;
    }



}

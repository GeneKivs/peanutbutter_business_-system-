package com.peanutbutter.peanutbutter.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peanutbutter.peanutbutter.dtos.CustomerRequestDto;
import com.peanutbutter.peanutbutter.dtos.CustomerResponseDto;
import com.peanutbutter.peanutbutter.service.CustomerServiceApi;

@RestController
@RequestMapping("/api/customers")
public class CustomerControllerApi {

    private final CustomerServiceApi customerServiceApi;

    public CustomerControllerApi(CustomerServiceApi customerServiceApi){
        this.customerServiceApi =customerServiceApi;
    }

    public ResponseEntity<CustomerResponseDto> createCustomer(@RequestBody CustomerRequestDto requestDto){
        CustomerResponseDto responseDto = customerServiceApi.createCustomer(requestDto);

        return new ResponseEntity<>(responseDto,HttpStatus.CREATED);
    }

    public ResponseEntity<CustomerResponseDto> getCustomer(@PathVariable Long customerID){
        CustomerResponseDto responseDto = customerServiceApi.getCustomerByID(customerID);

        return responseDto != null? ResponseEntity.ok(responseDto):ResponseEntity.notFound().build();
    }

    public ResponseEntity<CustomerResponseDto> updateCustomer(@PathVariable Long customerID,@RequestBody CustomerRequestDto requestDto){
        CustomerResponseDto responseDto = customerServiceApi.updateCustomer(customerID, requestDto);

        return ResponseEntity.ok(responseDto);
    }

    public ResponseEntity<List<CustomerResponseDto>> getCustomers(){
        List<CustomerResponseDto> responseDtos = customerServiceApi.getAllCustomers();

        return ResponseEntity.ok(responseDtos);
    }

}

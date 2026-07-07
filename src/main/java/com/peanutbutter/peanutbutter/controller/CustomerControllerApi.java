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

import com.peanutbutter.peanutbutter.dtos.CustomerRequestDto;
import com.peanutbutter.peanutbutter.dtos.CustomerResponseDto;
import com.peanutbutter.peanutbutter.service.CustomerServiceApi;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
public class CustomerControllerApi {

    private final CustomerServiceApi customerServiceApi;

    public CustomerControllerApi(CustomerServiceApi customerServiceApi){
        this.customerServiceApi =customerServiceApi;
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDto> createCustomer(@RequestBody CustomerRequestDto requestDto){
        CustomerResponseDto responseDto = customerServiceApi.createCustomer(requestDto);

        return new ResponseEntity<>(responseDto,HttpStatus.CREATED);
    }

    @GetMapping("/{customerID}")
    public ResponseEntity<CustomerResponseDto> getCustomer(@PathVariable Long customerID){
        CustomerResponseDto responseDto = customerServiceApi.getCustomerByID(customerID);

        return responseDto != null? ResponseEntity.ok(responseDto):ResponseEntity.notFound().build();
    }

    @PatchMapping("/{customerID}")
    public ResponseEntity<CustomerResponseDto> updateCustomer(@PathVariable Long customerID,@RequestBody CustomerRequestDto requestDto){
        CustomerResponseDto responseDto = customerServiceApi.updateCustomer(customerID, requestDto);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDto>> getCustomers(){
        List<CustomerResponseDto> responseDtos = customerServiceApi.getAllCustomers();

        return ResponseEntity.ok(responseDtos);
    }

}

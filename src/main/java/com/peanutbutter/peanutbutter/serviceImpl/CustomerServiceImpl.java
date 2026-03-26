package com.peanutbutter.peanutbutter.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.peanutbutter.peanutbutter.model.Customer;
import com.peanutbutter.peanutbutter.dtos.CustomerRequestDto;
import com.peanutbutter.peanutbutter.dtos.CustomerResponseDto;
import com.peanutbutter.peanutbutter.mapper.CustomerMapper;
import com.peanutbutter.peanutbutter.repository.CustomerRepository;
import com.peanutbutter.peanutbutter.service.CustomerServiceApi;

@Service
public class CustomerServiceImpl implements CustomerServiceApi {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponseDto createCustomer(CustomerRequestDto requestDto){
        Customer customer = CustomerMapper.toEntity(requestDto);
        Customer savedCustomer = customerRepository.save(customer);

        return CustomerMapper.toResponse(savedCustomer);
    }

    @Override
    public CustomerResponseDto getCustomerByID(Long customerID){
        return customerRepository.findById(customerID)
                .map(CustomerMapper::toResponse)
                .orElseThrow();
    }

    @Override
    public CustomerResponseDto updateCustomer(Long customerID,CustomerRequestDto requestDto){
        Customer existingCustomer = customerRepository.findById(customerID).orElseThrow(null);

        if (requestDto.getFirstName() != null) {
            existingCustomer.setFirstName(requestDto.getFirstName());
            
        }
        if (requestDto.getLastName() != null) {
            existingCustomer.setLastName(requestDto.getLastName());
        }

        if (requestDto.getPhoneNumber() != null) {
            existingCustomer.setPhoneNumber(requestDto.getPhoneNumber());
        }

        Customer updatedCustomer =customerRepository.save(existingCustomer);
         
        return CustomerMapper.toResponse(updatedCustomer);
    }


    @Override
    public List<CustomerResponseDto> getAllCustomers(){
        return customerRepository.findAll()
                .stream()
                .map(CustomerMapper::toResponse)
                .collect(Collectors.toList());
    }

}

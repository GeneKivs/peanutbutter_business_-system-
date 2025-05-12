package com.peanutbutter.peanutbutter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peanutbutter.peanutbutter.model.Customer;
import com.peanutbutter.peanutbutter.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void defineCustomer(Customer customer){
        customerRepository.save(customer);
    }

    public List<Customer> getAllCuatomers(){
        return customerRepository.findAll();
    }

    public void updateCustomer(Customer customer){
        customerRepository.save(customer);
    }

    public void deleteCustomerByID(int customerID){
        customerRepository.deleteById(customerID);
    }

}

package com.peanutbutter.peanutbutter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peanutbutter.peanutbutter.model.Payment;
import com.peanutbutter.peanutbutter.repository.PaymentRepository;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public void definePayment(Payment payment){
        paymentRepository.save(payment);
    }

    public Payment getPaymentByID(int paymentID){
        return paymentRepository.findById(paymentID).orElse(null);
    }

    public List<Payment> getAllPaymentTypes(){
        return paymentRepository.findAll();
    }

    public void updatePaymentType(Payment payment){
        paymentRepository.save(payment);
    }

    public void deletePaymentTypeByID(int paymanetID){
        paymentRepository.deleteById(paymanetID);
    }

}

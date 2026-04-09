package com.peanutbutter.peanutbutter.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.peanutbutter.peanutbutter.dtos.PaymentRequestDto;
import com.peanutbutter.peanutbutter.dtos.PaymentResponseDto;
import com.peanutbutter.peanutbutter.mapper.PaymentMapper;
import com.peanutbutter.peanutbutter.model.Payment;
import com.peanutbutter.peanutbutter.model.PaymentMethod;
import com.peanutbutter.peanutbutter.model.Sales;
import com.peanutbutter.peanutbutter.repository.PaymentMethodRepository;
import com.peanutbutter.peanutbutter.repository.PaymentRepository;
import com.peanutbutter.peanutbutter.repository.SalesRepository;
import com.peanutbutter.peanutbutter.service.PaymentServiceApi;

@Service
public class PaymentServiceImpl implements PaymentServiceApi{

    private final PaymentMethodRepository paymentMethodRepository;
    private final PaymentRepository paymentRepository;
    private final SalesRepository salesRepository;

    public PaymentServiceImpl(PaymentMethodRepository paymentMethodRepository,PaymentRepository paymentRepository,SalesRepository salesRepository){
        this.paymentMethodRepository = paymentMethodRepository;
        this.paymentRepository = paymentRepository;
        this.salesRepository =salesRepository;
    }

    @Override
    public PaymentResponseDto makePayment(PaymentRequestDto requestDto){
        PaymentMethod paymentMethod = paymentMethodRepository.findById(requestDto.getPaymentMethodID()).orElseThrow(() -> new RuntimeException("PaymentMethod not found"));
        Sales sales = salesRepository.findById(requestDto.getSalesID()).orElseThrow(() -> new RuntimeException("Sales not found"));
        
        Payment payment = new Payment();
        payment.setSales(sales);
        payment.setPaymentMethod(paymentMethod);
        payment.setPaymentDate(requestDto.getPaymentDate());
        payment.setAmount(requestDto.getAmount());

        Payment savedPayment = paymentRepository.save(payment);

        return PaymentMapper.toResponseDto(savedPayment);
    }

    @Override
    public PaymentResponseDto getPaymentByID(Long paymentID){
        return paymentRepository.findById(paymentID)
                .map(PaymentMapper::toResponseDto)
                .orElseThrow(()-> new RuntimeException("Payment not found"));
    }

    @Override
    public List<PaymentResponseDto> getAllPayments(){
        return paymentRepository.findAll()
                .stream()
                .map(PaymentMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public PaymentResponseDto patchPayment(Long paymentID,PaymentRequestDto requestDto){
        Payment existingPayment = paymentRepository.findById(paymentID).orElseThrow(() -> new RuntimeException("Payment not found"));

        if (requestDto.getPaymentDate() != null) {
            existingPayment.setPaymentDate(requestDto.getPaymentDate());
        }

        if (requestDto.getPaymentMethodID() != null) {
            PaymentMethod paymentMethod = paymentMethodRepository.findById(requestDto.getPaymentMethodID()).orElseThrow(() -> new RuntimeException("PaymentMethod not found"));
            existingPayment.setPaymentMethod(paymentMethod);
        }

        existingPayment.setAmount(requestDto.getAmount());  

        return PaymentMapper.toResponseDto(paymentRepository.save(existingPayment));


    }



}

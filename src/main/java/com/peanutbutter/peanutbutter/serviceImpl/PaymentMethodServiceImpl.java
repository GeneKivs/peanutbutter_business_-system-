package com.peanutbutter.peanutbutter.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.peanutbutter.peanutbutter.dtos.PaymentMethodRequestDto;
import com.peanutbutter.peanutbutter.dtos.PaymentMethodResponseDto;
import com.peanutbutter.peanutbutter.mapper.PaymentMethodMapper;
import com.peanutbutter.peanutbutter.model.PaymentMethod;
import com.peanutbutter.peanutbutter.repository.PaymentMethodRepository;
import com.peanutbutter.peanutbutter.service.PaymentMethodService;



@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodServiceImpl(PaymentMethodRepository paymentMethodRepository){
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Override
    public PaymentMethodResponseDto createPaymentMethod(PaymentMethodRequestDto requestDto){
        PaymentMethod paymentMethod = PaymentMethodMapper.toEntity(requestDto);

        PaymentMethod savedPaymentMethod = paymentMethodRepository.save(paymentMethod);

        return PaymentMethodMapper.toResponse(savedPaymentMethod);
    }

    @Override
    public PaymentMethodResponseDto getPaymentMethodByID(Long PaymentMethodID){
        return paymentMethodRepository.findById(PaymentMethodID)
                .map(PaymentMethodMapper::toResponse)
                .orElseThrow();
    }

    @Override
    public List<PaymentMethodResponseDto> getAllPaymentMethods(){
        return paymentMethodRepository.findAll()
                .stream()
                .map(PaymentMethodMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentMethodResponseDto updatePaymentMethod(Long paymentMethodID,PaymentMethodRequestDto requestDto){
        PaymentMethod existingPaymentMethod = paymentMethodRepository.findById(paymentMethodID).orElseThrow();

        if (requestDto.getPaymentType() != null) {
            existingPaymentMethod.setPaymentType(requestDto.getPaymentType());
        }

        PaymentMethod updatedMePaymentMethod = paymentMethodRepository.save(existingPaymentMethod);

        return PaymentMethodMapper.toResponse(updatedMePaymentMethod);
    }   


}

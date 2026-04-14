package com.peanutbutter.peanutbutter.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.peanutbutter.peanutbutter.dtos.PurchaseRequestDto;
import com.peanutbutter.peanutbutter.dtos.PurchaseResponseDto;
import com.peanutbutter.peanutbutter.mapper.PurchaseMapper;

import com.peanutbutter.peanutbutter.model.PaymentMethod;
import com.peanutbutter.peanutbutter.model.Purchase;

import com.peanutbutter.peanutbutter.repository.PaymentMethodRepository;
import com.peanutbutter.peanutbutter.repository.PurchaseRepository;
import com.peanutbutter.peanutbutter.service.AccountingService;
import com.peanutbutter.peanutbutter.service.PurchaseService;

@Service
public class PurchaseServiceImpl implements PurchaseService{

    private final PurchaseRepository purchaseRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final AccountingService accountingService;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository,PaymentMethodRepository paymentMethodRepository,AccountingService accountingService){
        this.purchaseRepository = purchaseRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.accountingService = accountingService;
    }

    @Override
    public PurchaseResponseDto createPurchase(PurchaseRequestDto requestDto){
        PaymentMethod paymentMethod = paymentMethodRepository.findById(requestDto.getPaymentMethodID()).orElseThrow();

        Purchase purchase = new Purchase();
        purchase.setPurchaseDate(requestDto.getPurchaseDate());
        purchase.setPeanutQuantity(requestDto.getPeanutQuantity());
        purchase.setAmountPaid(requestDto.getAmounPaid());
        purchase.setPaymentMethod(paymentMethod);

        Purchase savedPurchase = purchaseRepository.save(purchase);

        accountingService.purchaseAccounting(savedPurchase);

        return PurchaseMapper.toResponse(savedPurchase);
    }

    @Override
    public PurchaseResponseDto getPurchaseByID(Long purchaseID){
        return purchaseRepository.findById(purchaseID)
                .map(PurchaseMapper::toResponse)
                .orElseThrow();
    }


    @Override
    public List<PurchaseResponseDto> getAllPurchases(){
        return purchaseRepository.findAll()
                .stream()
                .map(PurchaseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PurchaseResponseDto updatePurchase(Long purchaseID,PurchaseRequestDto requestDto){
        Purchase existingPurchase = purchaseRepository.findById(purchaseID).orElseThrow();

        if (requestDto.getAmounPaid() != null) {
            existingPurchase.setAmountPaid(requestDto.getAmounPaid());
        }
        if (requestDto.getPurchaseDate() != null) {
            existingPurchase.setPurchaseDate(requestDto.getPurchaseDate());
        }
        if (requestDto.getPaymentMethodID() != null) {
            PaymentMethod paymentMethod = paymentMethodRepository.findById(requestDto.getPaymentMethodID()).orElseThrow();
            existingPurchase.setPaymentMethod(paymentMethod);
        }

        existingPurchase.setPeanutQuantity(requestDto.getPeanutQuantity());

        return PurchaseMapper.toResponse(purchaseRepository.save(existingPurchase));
    }

    
}

package com.peanutbutter.peanutbutter.service;

import java.util.List;

import com.peanutbutter.peanutbutter.dtos.PurchaseRequestDto;
import com.peanutbutter.peanutbutter.dtos.PurchaseResponseDto;

public interface PurchaseService {

    PurchaseResponseDto createPurchase(PurchaseRequestDto requestDto);
    PurchaseResponseDto getPurchaseByID(Long purchaseID);
    List<PurchaseResponseDto> getAllPurchases();
    PurchaseResponseDto updatePurchase(Long purchaseID,PurchaseRequestDto requestDto);


}

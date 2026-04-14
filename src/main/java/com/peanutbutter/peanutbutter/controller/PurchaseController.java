package com.peanutbutter.peanutbutter.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peanutbutter.peanutbutter.dtos.PurchaseRequestDto;
import com.peanutbutter.peanutbutter.dtos.PurchaseResponseDto;
import com.peanutbutter.peanutbutter.service.PurchaseService;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService){
        this.purchaseService = purchaseService;
    }


    @PostMapping
    public ResponseEntity<PurchaseResponseDto> createPurchase(@RequestBody PurchaseRequestDto requestDto){
        PurchaseResponseDto responseDto = purchaseService.createPurchase(requestDto);

        return new ResponseEntity<>(responseDto,HttpStatus.CREATED);
    }

    @GetMapping("/{purchaseID}")
    public ResponseEntity<PurchaseResponseDto> getPurchase(@PathVariable Long purchaseID){
        PurchaseResponseDto responseDto = purchaseService.getPurchaseByID(purchaseID);

        return responseDto != null  ? ResponseEntity.ok(responseDto) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<PurchaseResponseDto>> getPurchases(){
        List<PurchaseResponseDto> responseDtos = purchaseService.getAllPurchases();

        return ResponseEntity.ok(responseDtos);
    }

    @PatchMapping("/{purchaseID}")
    public ResponseEntity<PurchaseResponseDto> updatePurchase(@PathVariable Long purchaseID,@RequestBody PurchaseRequestDto requestDto){
        PurchaseResponseDto responseDto = purchaseService.updatePurchase(purchaseID, requestDto);

        return ResponseEntity.ok(responseDto);
    }

}

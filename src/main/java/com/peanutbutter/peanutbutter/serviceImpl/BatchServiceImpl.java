package com.peanutbutter.peanutbutter.serviceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.peanutbutter.peanutbutter.dtos.BatchProductRequestDto;
import com.peanutbutter.peanutbutter.dtos.BatchRequestDto;
import com.peanutbutter.peanutbutter.dtos.BatchResponseDto;
import com.peanutbutter.peanutbutter.mapper.BatchMapper;
import com.peanutbutter.peanutbutter.model.Batch;
import com.peanutbutter.peanutbutter.model.BatchProduct;
import com.peanutbutter.peanutbutter.model.Product;
import com.peanutbutter.peanutbutter.model.Purchase;
import com.peanutbutter.peanutbutter.repository.BatchProductRepository;
import com.peanutbutter.peanutbutter.repository.BatchRepository;
import com.peanutbutter.peanutbutter.repository.ProductRepository;
import com.peanutbutter.peanutbutter.repository.PurchaseRepository;
import com.peanutbutter.peanutbutter.service.BatchServiceApi;

import jakarta.transaction.Transactional;

@Service
public class BatchServiceImpl implements BatchServiceApi{

    private final BatchRepository batchRepository;
    private final PurchaseRepository purchaseRepository;
    private final BatchProductRepository batchProductRepository;
    private final ProductRepository productRepository;

    public BatchServiceImpl (BatchRepository batchRepository,PurchaseRepository purchaseRepository,BatchProductRepository batchProductRepository,ProductRepository productRepository){
        this.batchRepository = batchRepository;
        this.purchaseRepository = purchaseRepository;
        this.batchProductRepository = batchProductRepository;
        this.productRepository = productRepository;
        
    }

    @Transactional
    @Override
    public BatchResponseDto createBatch(BatchRequestDto requestDto){
        Purchase purchase = purchaseRepository.findById(requestDto.getPurchaseID()).orElseThrow();

        Batch batch = new Batch();
        batch.setReceivedDate(requestDto.getReceivedDate());
        batch.setPurchase(purchase);
        

        Batch savedBatch = batchRepository.save(batch);

        List<BatchProduct> batchProducts = new ArrayList<>();

        for(BatchProductRequestDto bpRequestDto: requestDto.getProducts()){
            Product product = productRepository.findById(bpRequestDto.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));
             BatchProduct batchProduct = new BatchProduct();
             batchProduct.setBatch(savedBatch);
             batchProduct.setProduct(product);
             batchProduct.setProductQuantity(bpRequestDto.getProductQuantity());
             batchProduct.setProductRemQuantity(bpRequestDto.getProductQuantity());

             batchProducts.add(batchProduct);


        }

        calculateCostPerUnit(savedBatch, purchase, batchProducts);
        batchProductRepository.saveAll(batchProducts);

        return BatchMapper.toResponse(savedBatch); 
    
    }

    @Override
    public BatchResponseDto updateBatch(Long BatchID,BatchRequestDto batchRequestDto){
        Batch existingBatch = batchRepository.findById(BatchID).orElseThrow();

        if (batchRequestDto.getReceivedDate() != null) {
            existingBatch.setReceivedDate(batchRequestDto.getReceivedDate());
            
        }

        for(BatchProductRequestDto bpRequest:batchRequestDto.getProducts()){
            BatchProduct existingBp = batchProductRepository.findByBatch_BatchID(BatchID);
            if (bpRequest.getProductId() != null) {
                Product product = productRepository.findById(bpRequest.getProductId()).orElseThrow();
                existingBp.setProduct(product);
            }
            existingBp.setProductQuantity(bpRequest.getProductQuantity());

            batchProductRepository.save(existingBp);

        }

        return BatchMapper.toResponse(batchRepository.save(existingBatch));
    

    }

    @Override
    public BatchResponseDto getBatchByID(Long BatchID){
        return batchRepository.findById(BatchID)
            .map(BatchMapper::toResponse)
            .orElseThrow();
    }

    @Override
    public List<BatchResponseDto> getAllBatches(){
        return batchRepository.findAll()
                .stream()
                .map(BatchMapper::toResponse)
                .collect(Collectors.toList());
    }
    

    public  void calculateCostPerUnit(Batch batch, Purchase purchase,List<BatchProduct> batchProducts){

        Double totalProduced = 0.0;

        //total production of batch
        for(BatchProduct bProduct : batchProducts){
            double size = bProduct.getProduct().getSize();
            int quantity = bProduct.getProductQuantity();

            totalProduced += size * quantity;
        }

        double totalCost = purchase.getAmounPaid().doubleValue();
        double costPerGram = totalCost/totalProduced;

        //assign cost per tin
        for(BatchProduct bp : batchProducts){
            double size = bp.getProduct().getSize();
            double costPerTin = size * costPerGram;

            bp.setCostPerUnit(BigDecimal.valueOf(costPerTin));
        }

    }



}

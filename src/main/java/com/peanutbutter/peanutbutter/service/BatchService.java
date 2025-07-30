package com.peanutbutter.peanutbutter.service;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peanutbutter.peanutbutter.model.Batch;
import com.peanutbutter.peanutbutter.model.BatchProduct;
import com.peanutbutter.peanutbutter.model.Product;
import com.peanutbutter.peanutbutter.model.enums.BatchStatus;
import com.peanutbutter.peanutbutter.repository.BatchProductRepository;
import com.peanutbutter.peanutbutter.repository.BatchRepository;
import com.peanutbutter.peanutbutter.repository.ProductRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class BatchService {
    
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BatchProductRepository batchProductRepository;

    @Autowired
    private BatchRepository batchRepository;

    public void defineBatch(int peanutQuantity, BigDecimal amountPaid, String[] productIds, HttpServletRequest request) {
        //create new batch
        Batch batch = new Batch();
        batch.setPeanutQuantity(peanutQuantity);
        batch.setAmountPaid(amountPaid);

        // Get the transaction date from the form and set it as receivedDate
        String transactionDateStr = request.getParameter("transactionDate");
        if (transactionDateStr != null && !transactionDateStr.isEmpty()) {
            LocalDate transactionDate = LocalDate.parse(transactionDateStr, DateTimeFormatter.ISO_DATE);
            batch.setReceivedDate(transactionDate);
        } else {
            batch.setReceivedDate(LocalDate.now());
        }

        batchRepository.save(batch); //save first to generate an ID

        int totalTins = 0;

        //handle each selected product in the batch
        if (productIds != null) {
            for (String productId : productIds) {
                int productID = Integer.parseInt(productId);
                //get the quantity for each product from the request
                int quantity = Integer.parseInt(request.getParameter("quantity_" + productID));

                //find the product by ID
                Product product = productRepository.findById(productID).orElse(null);
                if(product !=null){
                    //create a batch product link and save it
                    BatchProduct batchProduct = new BatchProduct();
                    batchProduct.setBatch(batch);
                    batchProduct.setProduct(product);
                    batchProduct.setProductQuantity(quantity);
                    batchProduct.setProductRevenue(BigDecimal.ZERO);
                    batchProduct.setProductRemQuantity(quantity);

                    batchProductRepository.save(batchProduct);

                    //update the total tins counter
                    totalTins += quantity;

                    //update the product stock in products table
                    product.setQuantityInStock(product.getQuantityInStock() + quantity);
                    productRepository.save(product);
                }
            }
        }

        //update the batch with the total tins and remaining quantity
        batch.setTotalTins(totalTins);
        batch.setRemainingQuantity(totalTins);

        //update the batch profit, revenue and total expenditure
        batch.setProfit(BigDecimal.ZERO);
        batch.setRevenue(BigDecimal.ZERO);
        

        //update the batch status to available
        batch.setBatchStatus(BatchStatus.AVAILABLE);
        //save the batch again to update the total tins and remaining quantity
        batchRepository.save(batch);
    }

    public List<Batch> getAvailableBatches(){
        return batchRepository.findByBatchStatus(BatchStatus.AVAILABLE);
    }

    public Batch getBatchByID(int batchID){
        return batchRepository.findById(batchID).orElse(null);
    }

    public List<Batch> getAllBatches(){
        return batchRepository.findAll();
    }

    public void updateBatch(Batch batch){
        batchRepository.save(batch);
    }

}

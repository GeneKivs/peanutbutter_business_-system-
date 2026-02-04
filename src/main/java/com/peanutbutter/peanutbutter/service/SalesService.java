package com.peanutbutter.peanutbutter.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peanutbutter.peanutbutter.model.Batch;
import com.peanutbutter.peanutbutter.model.BatchProduct;
import com.peanutbutter.peanutbutter.model.Customer;
import com.peanutbutter.peanutbutter.model.Payment;
import com.peanutbutter.peanutbutter.model.Product;
import com.peanutbutter.peanutbutter.model.Sales;
import com.peanutbutter.peanutbutter.model.SalesProduct;
import com.peanutbutter.peanutbutter.model.enums.BatchStatus;
import com.peanutbutter.peanutbutter.model.enums.ProductStatus;
import com.peanutbutter.peanutbutter.model.enums.SalesStatus;
import com.peanutbutter.peanutbutter.repository.BatchProductRepository;
import com.peanutbutter.peanutbutter.repository.BatchRepository;
import com.peanutbutter.peanutbutter.repository.ProductRepository;
import com.peanutbutter.peanutbutter.repository.SalesProductRepository;
import com.peanutbutter.peanutbutter.repository.SalesRepository;

@Service
public class SalesService {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private BatchProductRepository batchProductRepository;

    @Autowired
    private SalesProductRepository salesProductRepository;

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private AccountService accountService;

    public void createSalesOrder(Customer customer, LocalDate salesOrderDate, Map<Integer,Integer> productQuantityMap){
        Sales sales = new Sales();
        sales.setCustomer(customer);
        sales.setSalesOrderDate(salesOrderDate); // use the provided date
       
        sales.setTotalAmount(BigDecimal.ZERO);
        sales.setAmountPaid(BigDecimal.ZERO);
        sales.setDeliveryCost(BigDecimal.ZERO);
        sales.setBalance(BigDecimal.ZERO);
        sales.setSalesStatus(SalesStatus.PENDING);
        salesRepository.save(sales);

        processSalesOrder(sales, productQuantityMap);
    }

    public void processSalesOrder(Sales sales,Map<Integer,Integer> productQuantityMap){
        BigDecimal totalAmount = BigDecimal.ZERO;

        for(Map.Entry<Integer,Integer> entry : productQuantityMap.entrySet()){
            int productID = entry.getKey();
            int quantityNeeded = entry.getValue();

            Product product = productRepository.findById(productID).orElse(null);
            if(product == null || product.getQuantityInStock() < quantityNeeded){
                continue;
            }

            int newProductStock = product.getQuantityInStock() - quantityNeeded;
            product.setQuantityInStock(newProductStock);
            //upate product stock status
            if(newProductStock == 0){
                product.setProductStatus(ProductStatus.UNAVAILABLE);
            }else if(newProductStock == product.getReorderLevel() ){
                product.setProductStatus(ProductStatus.LOW_STOCK);
            }
            productRepository.save(product);

            //FIFO across batches
            List<Batch> batches = batchRepository.findAvailableBatchesByProductID(productID);
            for(Batch batch : batches){
                if(quantityNeeded <= 0) break;

                BatchProduct batchProduct = batchProductRepository.findByBatch_BatchIDAndProduct_ProductID(batch.getBatchID(), productID);
                if(batchProduct == null || batchProduct.getProductRemQuantity() <= 0) continue;

                int availableFromBatchProduct = batchProduct.getProductRemQuantity();
                int quantityFromThisBatch = Math.min(quantityNeeded, availableFromBatchProduct);

                batchProduct.setProductRemQuantity(availableFromBatchProduct -  quantityFromThisBatch);
                batchProductRepository.save(batchProduct);

                //update batch
               int newRemaining =batchProductRepository.sumRemainingQuantityByBatchID(batch.getBatchID());
               batch.setRemainingQuantity(newRemaining);
               
               if(newRemaining == 0){
                batch.setBatchStatus(BatchStatus.COMPLETED);
               }
                
                batchRepository.save(batch);

                

                BigDecimal totalproduct = product.getPricePerTin().multiply(new BigDecimal(quantityFromThisBatch));
                
                //update sales product
                SalesProduct salesProduct = new SalesProduct();
                salesProduct.setSales(sales);
                salesProduct.setBatch(batch);
                salesProduct.setProduct(product);
                salesProduct.setQuantity(quantityFromThisBatch);
                salesProduct.setTotal(totalproduct);
                salesProductRepository.save(salesProduct);

                totalAmount = totalAmount.add(totalproduct);
                quantityNeeded -= quantityFromThisBatch;
            }
        }

        //update totsls in sales
        sales.setTotalAmount(totalAmount);
        sales.setBalance(totalAmount);
        salesRepository.save(sales);
        
                accountService.addToSalesAccount(totalAmount); // Add amount paid to Sales Account


    }

    public void processDelivery(long salesid, BigDecimal amountPaid, BigDecimal deliveryCost, int paymentID, LocalDate deliveryDate) {
        Sales sales = salesRepository.findById(salesid).orElse(null);
        if (sales == null) {
            throw new RuntimeException("Sales order not found");
        }

        BigDecimal totalamount = sales.getTotalAmount();
        BigDecimal totalPaid = amountPaid.add(deliveryCost != null ? deliveryCost : BigDecimal.ZERO);
        BigDecimal balance = totalamount.subtract(amountPaid);

        sales.setAmountPaid(totalPaid);
        if (deliveryCost != null) {
            sales.setDeliveryCost(deliveryCost);
        } else {
            sales.setDeliveryCost(BigDecimal.ZERO);
        }
        sales.setBalance(balance);
        if (balance.compareTo(BigDecimal.ZERO) == 0) {
            sales.setSalesStatus(SalesStatus.COMPLETE);
        } else {
            sales.setSalesStatus(SalesStatus.INCOMPLETE);
        }
        sales.setDeliveryDate(deliveryDate); // use the provided date

        Payment payment = paymentService.getPaymentByID(paymentID);
        String paymentName= payment.getPaymentType();
        accountService.addAccountBalance(paymentName,totalPaid);
        sales.setPayment(payment);

        // Add or update customer account for remaining balance (debt)
        if (balance.compareTo(BigDecimal.ZERO) > 0) {
            Customer customer = sales.getCustomer();
            if (customer != null) {
                String customerAccountName = customer.getCustomerName();
                accountService.addOrCreateCustomerAccountBalance(customerAccountName, balance);
            }
        }

        //save revenue to batch
        List<SalesProduct> salesProducts = salesProductRepository.findBySales(sales);
        for (SalesProduct salesProduct : salesProducts) {
            Batch batch = salesProduct.getBatch();

            BigDecimal existingRevenue = batch.getRevenue();
            BigDecimal newRevenue = existingRevenue.add(salesProduct.getTotal());
            batch.setRevenue(newRevenue); 
            if (batch.getBatchStatus().equals(BatchStatus.COMPLETED)) {
               
                BigDecimal amountPay = batch.getAmountPaid();
                BigDecimal calculatedRevenue = batch.getRevenue();

                BigDecimal calculateProfit = calculatedRevenue.subtract(amountPay);
                batch.setProfit(calculateProfit);
                batch.setEndDate(deliveryDate);

            }
        }

        salesRepository.save(sales);
        
    }

    public List<Sales> getALLSales(){
        return salesRepository.findAll();
    }

    public List<Sales> getPendingSales(){
        return salesRepository.findBySalesStatus(SalesStatus.PENDING);
    }

    public int getProductCount(Sales sales, String productName){
        if (sales.getSalesProducts() == null) return 0;
        return sales.getSalesProducts().stream()
                .filter(sp -> sp.getProduct() != null && productName.equals(sp.getProduct().getProductName()))
                .mapToInt(SalesProduct::getQuantity)
                .sum(); 
    }
    public void updateSales(Sales sales){
        salesRepository.save(sales);
    }

}

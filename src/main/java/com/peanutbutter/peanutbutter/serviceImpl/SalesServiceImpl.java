package com.peanutbutter.peanutbutter.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.peanutbutter.peanutbutter.dtos.PaymentRequestDto;
import com.peanutbutter.peanutbutter.dtos.PaymentResponseDto;
import com.peanutbutter.peanutbutter.dtos.SalesProductRequestDto;
import com.peanutbutter.peanutbutter.dtos.SalesProductResponseDto;
import com.peanutbutter.peanutbutter.dtos.SalesRequestDto;
import com.peanutbutter.peanutbutter.dtos.SalesResponseDto;
import com.peanutbutter.peanutbutter.model.BatchProduct;
import com.peanutbutter.peanutbutter.model.Customer;
import com.peanutbutter.peanutbutter.model.Payment;
import com.peanutbutter.peanutbutter.model.PaymentMethod;
import com.peanutbutter.peanutbutter.model.Sales;
import com.peanutbutter.peanutbutter.model.SalesProduct;
import com.peanutbutter.peanutbutter.repository.BatchProductRepository;
import com.peanutbutter.peanutbutter.repository.BatchRepository;
import com.peanutbutter.peanutbutter.repository.CustomerRepository;
import com.peanutbutter.peanutbutter.repository.PaymentMethodRepository;
import com.peanutbutter.peanutbutter.repository.PaymentRepository;
import com.peanutbutter.peanutbutter.repository.SalesProductRepository;
import com.peanutbutter.peanutbutter.repository.SalesRepository;

import jakarta.transaction.Transactional;

@Service
public class SalesServiceImpl {

    private final SalesRepository salesRepository;
    private final SalesProductRepository salesProductRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final BatchProductRepository batchProductRepository;
    private final CustomerRepository customerRepository;

    public SalesServiceImpl(SalesRepository salesRepository,SalesProductRepository salesProductRepository,PaymentMethodRepository paymentMethodRepository,PaymentRepository paymentRepository,BatchProductRepository batchProductRepository,BatchRepository batchRepository,CustomerRepository customerRepository){
        this.batchProductRepository = batchProductRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.paymentRepository = paymentRepository;
        this.salesProductRepository = salesProductRepository;
        this.salesRepository = salesRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public SalesResponseDto processSales(SalesRequestDto requestDto){
        //find the customer involved
        Customer customer = customerRepository.findById(requestDto.getCustomerID()).orElseThrow(()-> new RuntimeException("Customer not found"));

        
        Sales sales = new Sales();
        sales.setCustomer(customer);
        sales.setSalesOrderDate(requestDto.getSalesDate());

        Sales savedSales = salesRepository.save(sales);

        List<SalesProductResponseDto> responseDtos = new ArrayList<>();
        List<PaymentResponseDto> responsePayments = new ArrayList<>();

        for(SalesProductRequestDto spRequestDto: requestDto.getSaleProducts()){

            Integer quantityToSell = spRequestDto.getQuantity();

            List<BatchProduct> batchProducts = batchProductRepository.findByProductIDAndProductRemQuantity(spRequestDto.getProductID());

            if (batchProducts.isEmpty()) {
                throw new RuntimeException("No stock available for product: " + spRequestDto.getProductID());
            }

            for(BatchProduct bProduct : batchProducts){

                if (quantityToSell <= 0) break;
                
                int available = bProduct.getProductRemQuantity();

                if(available <= 0) continue;

                int quantityUsed = Math.min(available, quantityToSell);


                bProduct.setProductRemQuantity(available - quantityUsed);
                batchProductRepository.save(bProduct);

                SalesProduct salesProduct = new SalesProduct();
                salesProduct.setSales(savedSales);
                salesProduct.setProducts(bProduct);
                salesProduct.setQuantity(quantityUsed);
                salesProduct.setSellingPrice(spRequestDto.getSellingPrice());

                salesProductRepository.save(salesProduct);

                SalesProductResponseDto  responseProduct = new SalesProductResponseDto();
                responseProduct.setProductID(spRequestDto.getProductID());
                responseProduct.setQuantity(quantityUsed);
                responseProduct.setSellingPrice(spRequestDto.getSellingPrice());

                responseDtos.add(responseProduct);

                quantityToSell -= quantityUsed;



            }

            if (quantityToSell > 0) {
                throw new RuntimeException("Insufficient stock for product: " + spRequestDto.getProductID());
            }

        }

        for(PaymentRequestDto paymentRequestDto: requestDto.getPayments()){
            PaymentMethod paymentMethod = paymentMethodRepository.findById(paymentRequestDto.getPaymentMethodID()).orElseThrow(() -> new RuntimeException("PaymentMethod not found"));

            Payment payment = new Payment();
            payment.setPaymentDate(requestDto.getSalesDate());
            payment.setPaymentMethod(paymentMethod);
            payment.setSales(savedSales);
            payment.setAmount(paymentRequestDto.getAmount());

            paymentRepository.save(payment);

            PaymentResponseDto responsePayment = new PaymentResponseDto();
            responsePayment.setPaymentDate(paymentRequestDto.getPaymentDate());
            responsePayment.setPaymentType(payment.getPaymentMethod().getPaymentType());
            responsePayment.setAmount(paymentRequestDto.getAmount());

            responsePayments.add(responsePayment);

        }

        SalesResponseDto response = new SalesResponseDto();
        response.setSalesID(savedSales.getSalesid());
        response.setSalesDate(savedSales.getSalesOrderDate());
        response.setCustomerName(savedSales.getCustomer().getCustomerName());
        response.setProducts(responseDtos);
        response.setPayment(responsePayments);


        return response;



    }



}

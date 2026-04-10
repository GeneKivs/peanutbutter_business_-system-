package com.peanutbutter.peanutbutter.service;

import com.peanutbutter.peanutbutter.dtos.SalesRequestDto;
import com.peanutbutter.peanutbutter.dtos.SalesResponseDto;
import com.peanutbutter.peanutbutter.model.Payment;
import com.peanutbutter.peanutbutter.model.Sales;

public interface SalesServiceApi {

    SalesResponseDto processSales(SalesRequestDto requestDto); 
    void salesAccounting (Sales sales,Payment payment);

}

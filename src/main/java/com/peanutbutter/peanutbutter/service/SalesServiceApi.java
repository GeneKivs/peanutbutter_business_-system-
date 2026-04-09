package com.peanutbutter.peanutbutter.service;

import com.peanutbutter.peanutbutter.dtos.SalesRequestDto;
import com.peanutbutter.peanutbutter.dtos.SalesResponseDto;

public interface SalesServiceApi {

    SalesResponseDto processSales(SalesRequestDto requestDto); 

}

package com.peanutbutter.peanutbutter.service;

import java.util.List;

import com.peanutbutter.peanutbutter.dtos.ExpenditureRequestDto;
import com.peanutbutter.peanutbutter.dtos.ExpenditureResponseDto;

public interface ExpenditureServiceApi {

    ExpenditureResponseDto createExpenditure(ExpenditureRequestDto requestDto);

    ExpenditureResponseDto getExpenditureById(Long expenditureID);

    List<ExpenditureResponseDto> getAllExpenditures();

    ExpenditureResponseDto updateExpenditure(Long expenditureID, ExpenditureRequestDto requestDto);

    

}

package com.peanutbutter.peanutbutter.service;

import com.peanutbutter.peanutbutter.dtos.AccountRequestDto;
import com.peanutbutter.peanutbutter.dtos.AccountResponseDto;

public interface AccountService {

    AccountResponseDto createAccount(AccountRequestDto requestDto);

}

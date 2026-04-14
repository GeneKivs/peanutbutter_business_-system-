package com.peanutbutter.peanutbutter.serviceImpl;

import org.springframework.stereotype.Service;

import com.peanutbutter.peanutbutter.dtos.AccountRequestDto;
import com.peanutbutter.peanutbutter.dtos.AccountResponseDto;
import com.peanutbutter.peanutbutter.mapper.AccountMapper;
import com.peanutbutter.peanutbutter.model.Account;
import com.peanutbutter.peanutbutter.repository.AccountRepository;
import com.peanutbutter.peanutbutter.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    public AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public AccountResponseDto createAccount(AccountRequestDto requestDto){
        Account account = AccountMapper.toEntity(requestDto);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.toResponse(savedAccount);
    }

}

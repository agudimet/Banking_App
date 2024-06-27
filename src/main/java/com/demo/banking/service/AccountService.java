package com.demo.banking.service;

import com.demo.banking.dto.AccountDto;

import java.util.List;


public interface AccountService {


    //creat methods which are implemented in AccountsService class
    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);

    AccountDto deposit(Long id,double amount);

    AccountDto withdraw(Long id, double amount);

    List<AccountDto> getAllAccounts();

    void deleteAccount(Long id);
}

package com.demo.banking.impl;

import com.demo.banking.dto.AccountDto;
import com.demo.banking.entity.Account;
import com.demo.banking.mapper.AccountMapper;
import com.demo.banking.repository.AccountRepository;
import com.demo.banking.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {


    //in this class implemented all the methods which are performed in banking app
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto)
    {
        Account account = AccountMapper.mapToAccount(accountDto);
       Account saveAccount=accountRepository.save(account);

        return AccountMapper.mapToAccountDto(saveAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
       Account account = accountRepository
               .findById(id)
               .orElseThrow(()-> new RuntimeException("Account Id does not present"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account Id does not present"));

      double total =  account.getBalance() +amount;
      account.setBalance(total);
     Account savedAccount  = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account Id does not present"));
        if(account.getBalance()< amount){
           throw  new RuntimeException("Insufficient balance");
        }
        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account saveAmount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(saveAmount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map((account)-> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account Id does not present"));
        accountRepository.deleteById(id);

    }
}

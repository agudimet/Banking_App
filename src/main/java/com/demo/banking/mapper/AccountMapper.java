package com.demo.banking.mapper;

import com.demo.banking.dto.AccountDto;
import com.demo.banking.entity.Account;

public class AccountMapper {


    //mapping the account class to accountSto and vice virsa
    public static Account mapToAccount(AccountDto accountDto){
        Account account = new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance()
        );
        return account;
    }

    public static AccountDto mapToAccountDto(Account account){
        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );
        return accountDto;

    }
}

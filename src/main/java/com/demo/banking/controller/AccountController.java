package com.demo.banking.controller;


import com.demo.banking.dto.AccountDto;
import com.demo.banking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {


    //logic to connect to the database url and status code
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //Add Account REST api
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    //Get account REST api
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountByID(@PathVariable Long id){
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    //deposit REST api
    @PutMapping("/{id}/deposit")
    public  ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String,Double> request){
       Double amount = request.get("amount");
      AccountDto accountDto=  accountService.deposit(id, amount);
      return  ResponseEntity.ok(accountDto);

    }
  //withdraw REST api
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,@RequestBody Map<String,Double> request){
        Double amount = request.get("amouunt");
        AccountDto accountDto = accountService.withdraw(id, amount);
        return ResponseEntity.ok(accountDto);

    }

    //Get all accounts REST api
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> accounts = accountService.getAllAccounts();
        return  ResponseEntity.ok(accounts);
    }

    //delete account REST api
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
       return  ResponseEntity.ok("Account deleted Successfully");
    }
}

package com.demo.banking.repository;

import com.demo.banking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    //this AccountRepository interface extends the root interface of JpaRepository which contains 2 parameters of 1st is the entity name and
    // 2nd datatype used in that account class
    //so that AccountRepository can get to perform the CRUD operations
}

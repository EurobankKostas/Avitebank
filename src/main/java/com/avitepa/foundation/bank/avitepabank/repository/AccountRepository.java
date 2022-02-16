package com.avitepa.foundation.bank.avitepabank.repository;

import com.avitepa.foundation.bank.avitepabank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

     Optional<Account> getAccountByAccountNumber(String accountNumber);
}

package com.avitepa.foundation.bank.avitepabank.repository;

import com.avitepa.foundation.bank.avitepabank.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}

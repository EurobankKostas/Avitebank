package com.avitepa.foundation.bank.avitepabank.service;

import com.avitepa.foundation.bank.avitepabank.dto.CustomerDto;
import com.avitepa.foundation.bank.avitepabank.entity.Account;
import com.avitepa.foundation.bank.avitepabank.entity.Customer;
import com.avitepa.foundation.bank.avitepabank.entity.CustomerPersonalDetails;

import java.util.List;

public interface CustomerService {

    void createAccount(Account account, Long customerId) throws Exception;
    void editPersonalDetails(CustomerPersonalDetails customerPersonalDetails, Long customerId) throws Exception;
    void createCustomer(Customer customer) throws Exception;
    void transferFunds(long amount ,String accountNameSource, String accountTargetSource) throws Exception;
    List<CustomerDto> getCustomer(List<Long> customersIds);
}

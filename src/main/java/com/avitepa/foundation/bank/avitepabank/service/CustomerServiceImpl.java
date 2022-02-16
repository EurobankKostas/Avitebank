package com.avitepa.foundation.bank.avitepabank.service;

import com.avitepa.foundation.bank.avitepabank.dto.AccountDto;
import com.avitepa.foundation.bank.avitepabank.dto.CustomerDetails;
import com.avitepa.foundation.bank.avitepabank.dto.CustomerDto;
import com.avitepa.foundation.bank.avitepabank.entity.Account;
import com.avitepa.foundation.bank.avitepabank.entity.Customer;
import com.avitepa.foundation.bank.avitepabank.entity.CustomerPersonalDetails;
import com.avitepa.foundation.bank.avitepabank.exception.NotFoundException;
import com.avitepa.foundation.bank.avitepabank.repository.AccountRepository;
import com.avitepa.foundation.bank.avitepabank.repository.CustomerRepository;
import com.avitepa.foundation.bank.avitepabank.repository.PersonalDetailsRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService {
    private static final Logger logger = LogManager.getLogger(CustomerServiceImpl.class);

    @Autowired
    private PersonalDetailsRepo personalDetailsRepo;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void createAccount(Account account, Long customerId) throws Exception {
        Optional<Customer> customerFromRepo = customerRepository.findById(customerId);

        if (!customerFromRepo.isPresent() || customerFromRepo.get().getAccountList() == null) {
            logger.error("Not a valid customer , cannot proceed");
            throw new Exception("Not a valid customer , cannot proceed");
        }

        Optional<Account> cacheCustomer = customerFromRepo.get().getAccountList().stream().filter(e -> (e.getAccountType().code().equals(account.getAccountType().code())) || (e.getAccountNumber().equals(account.getAccountNumber()))).findAny();
        if (cacheCustomer.isPresent()) {
            logger.error("Same AccountType Or Same AccountName with existing account , cannot proceed");
            throw new Exception("Same AccountType Or Same AccountName , cannot proceed");
        }
        account.setCustomer(customerFromRepo.get());
        customerFromRepo.get().getAccountList().add(account);
        customerRepository.save(customerFromRepo.get());
    }

    @Override
    public void editPersonalDetails(CustomerPersonalDetails customerPersonalDetails, Long customerId) throws Exception {
        Optional<Customer> customerFromRepo = customerRepository.findById(customerId);
        if (customerFromRepo.isPresent() && customerFromRepo.get().getAccountList() != null) {
            Optional<CustomerPersonalDetails> customerPersonalDetailsRepo = personalDetailsRepo.findById(customerFromRepo.get().getCustomerPersonalDetails().getId());
            customerPersonalDetails.setId(customerPersonalDetailsRepo.get().getId());
            customerPersonalDetails.setCustomer(customerFromRepo.get());
            customerFromRepo.get().setCustomerPersonalDetails(customerPersonalDetails);
            //personalDetailsRepo.save(customerPersonalDetails);
            customerRepository.save(customerFromRepo.get());
            return;
        }
        logger.error("Client not found");
        throw new NotFoundException("Client not found");


    }

    @Override
    public void createCustomer(Customer customer) throws Exception {
        Optional<Customer> customerValid = customerRepository.findById(customer.getCustomerId());
        if(customerValid.isPresent()){
            logger.error("Customer already exists");
            throw new Exception("Customer already exists");
        }

        customer.getAccountList().forEach(e -> e.setCustomer(customer));
        customer.getCustomerPersonalDetails().setCustomer(customer);
        customer.setCustomerPersonalDetails(customer.getCustomerPersonalDetails());
        customer.setCustomerId(customer.getCustomerId());
        customerRepository.save(customer);
    }

    @Override
    public void transferFunds(long amount, String accountNameSource, String accountTargetSource) throws Exception {
        Optional<Account> account = accountRepository.getAccountByAccountNumber(accountNameSource);
        if (account.isPresent() && account.get().getAmount() >= amount) {
            Optional<Account> accountTarget = accountRepository.getAccountByAccountNumber(accountTargetSource);
            if(!accountTarget.isPresent() || (accountTarget.get().getCustomer().equals(account.get().getCustomer().getCustomerId()))){
                logger.error("Specific accounts are not referenced to Same Customer");
                throw new Exception("Specific accounts are not referenced to Same Customer");
            }
            logger.info("Trasactions amounts before transfer : " + account.get().getAmount() + " Target Amount : " + accountTarget.get().getAmount());
            account.get().setAmount(account.get().getAmount() - amount);
            accountTarget.get().setAmount(accountTarget.get().getAmount() + amount);
            //logger.info("Trasactions amounts after transfer: "+ account.get().getAmount() + " Target Amount : "+ accountTarget.get().getAmount());
            accountRepository.save(account.get());
            accountRepository.save(accountTarget.get());
        }
    }

    @Override
    public List<CustomerDto> getCustomer(List<Long> customersIds) {
        List<CustomerDto> customerDtoList = new ArrayList<>();
        List<Customer> customersFromRepo = customerRepository.findAllById(customersIds);
        List<AccountDto> accountDtos = new ArrayList<>();
        if (!customersFromRepo.isEmpty()) {
            customersFromRepo.stream().forEach(e -> {
                CustomerDetails customerPersonalDetails = CustomerDetails.builder().firstName(e.getCustomerPersonalDetails().getFirstName()).mobile(e.getCustomerPersonalDetails().getMobile()).gender(e.getCustomerPersonalDetails().getGender()).build();
                e.getAccountList().forEach(a -> {
                    AccountDto accountDto = AccountDto.builder().accountNumber(a.getAccountNumber()).accountType(a.getAccountType()).amount(a.getAmount()).dateCreated(a.getDateCreated()).build();
                    accountDtos.add(accountDto);
                });
                CustomerDto customerDto = CustomerDto.builder().customerId(e.getCustomerId()).customerPersonalDetails(customerPersonalDetails).accountList(accountDtos).build();
                customerDtoList.add(customerDto);
            });
        }
        return customerDtoList;
    }
}

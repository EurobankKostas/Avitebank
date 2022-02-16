package com.avitepa.foundation.bank.avitepabank.controller;

import com.avitepa.foundation.bank.avitepabank.dto.CustomerDto;
import com.avitepa.foundation.bank.avitepabank.dto.TransferRequest;
import com.avitepa.foundation.bank.avitepabank.entity.Account;
import com.avitepa.foundation.bank.avitepabank.entity.Customer;
import com.avitepa.foundation.bank.avitepabank.entity.CustomerPersonalDetails;
import com.avitepa.foundation.bank.avitepabank.dto.CustomerRequest;
import com.avitepa.foundation.bank.avitepabank.exception.NotFoundException;
import com.avitepa.foundation.bank.avitepabank.service.CustomerService;
import com.avitepa.foundation.bank.avitepabank.service.CustomerServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@RestController
@RequestMapping("/banking/v1")
public class BankingController {
    private static final Logger logger = LogManager.getLogger(BankingController.class);

    @Autowired
    private CustomerService customerService;

    @PostMapping("/create/customer")
    private ResponseEntity<String> createCustomer(@RequestBody Customer customer) throws Exception {
        logger.info("Start creating customer");
        if(customer == null || customer.getAccountList() == null || customer.getCustomerPersonalDetails() == null){
            logger.error("Not a valid customer input");
            throw new NotFoundException("Not a valid customer input");
        }
        customerService.createCustomer(customer);
        return new ResponseEntity<>("Customer Created",HttpStatus.CREATED);
    }

    @PostMapping("/create/customer/{customerId}/account")
    private ResponseEntity<String> createAccount(@RequestBody Account account , @PathVariable Long customerId) throws Exception {
        logger.info("Start creating account");
        if(account == null){
            logger.error("Not a valid account input");
            throw new NotFoundException("Not a valid account input");
        }
        customerService.createAccount(account,customerId);
        logger.info("Account created");
        return new ResponseEntity<>("Account created",HttpStatus.CREATED);
    }

    @PutMapping("/create/customer/{customerId}/personalDetails")
    private ResponseEntity<String> editPersonalDetails(@RequestBody CustomerPersonalDetails customerPersonalDetails , @PathVariable Long customerId) throws Exception {
        logger.info("Start editPersonalDetails");
        if(customerPersonalDetails == null){
            logger.error("Start creating account");
            throw new NotFoundException("Not valid customerPersonalDetails input");
        }
        customerService.editPersonalDetails(customerPersonalDetails,customerId);
        logger.info("EditPersonalDetails completed");
        return new ResponseEntity<>("Personal Details Changed",HttpStatus.CREATED);
    }

    @GetMapping("/create/customer/personalDetails")
    private @ResponseBody ResponseEntity<List<CustomerDto>> getCustomers(@RequestBody CustomerRequest customerRequest) throws Exception {
        logger.info("Start getCustomers");
        if(customerRequest == null){
            logger.error("Not valid customerIds input");
            throw new NotFoundException("Not valid customerIds input");
        }
        return new ResponseEntity<>(customerService.getCustomer(customerRequest.getCustomerIdsInput()),HttpStatus.OK);
    }

    @PostMapping("/create/customer/transferFunds")
    private @ResponseBody ResponseEntity<String> transferFunds(@RequestBody TransferRequest transferRequest) throws Exception {
        logger.info("Start transferFunds");
        if(transferRequest == null){
            logger.error("Not valid customerIds input");
            throw new NotFoundException("Not valid customerIds input");
        }
        customerService.transferFunds(transferRequest.getAmount(),transferRequest.getSourceAccountId(),transferRequest.getTargetAccountId());
        logger.info("TransferFunds completed");
        return new ResponseEntity<>("SuccessFull Transactions",HttpStatus.OK);
    }
}

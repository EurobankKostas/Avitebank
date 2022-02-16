package com.avitepa.foundation.bank.avitepabank;


import com.avitepa.foundation.bank.avitepabank.entity.Customer;
import com.avitepa.foundation.bank.avitepabank.repository.CustomerRepository;
import com.avitepa.foundation.bank.avitepabank.service.CustomerServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import java.util.List;
import java.util.Optional;



@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRepo {
    private static final Logger logger = LogManager.getLogger(TestRepo.class);

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void testFindCustomer(){
        Optional<Customer> customer = customerRepository.findById(1L);
        assertNotNull(customer);
    }

    @Test
    public void testFindAllCustomer(){
        List<Customer> customersTest = customerRepository.findAll();
        assertNotNull(customersTest);
    }

    @Test
    public void testDeleteCustomer(){
        Customer customer= new Customer();
        customer.setCustomerId(1L);
        customerRepository.save(customer);
        customerRepository.deleteById(1L);

    }

    @Test
    public void testDeleteAll(){
        Customer customer= new Customer();
        customer.setCustomerId(1L);
        Customer customer2= new Customer();
        customer.setCustomerId(2L);
        customerRepository.save(customer);
        customerRepository.save(customer2);
        customerRepository.deleteAll();
    }

    @Test
    public void testSave(){
        Customer customer= new Customer();
        customer.setCustomerId(1L);
        logger.info("Start mock save");
        customerRepository.save(customer);
        Optional<Customer> customerTest = customerRepository.findById(1L);
        assertNotNull(customerTest);
    }

}

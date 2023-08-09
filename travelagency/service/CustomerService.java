package com.bergeskar.travelagency.service;


import com.bergeskar.travelagency.exception.ResourceNotFoundException;
import com.bergeskar.travelagency.model.Customer;
import com.bergeskar.travelagency.model.Destination;
import com.bergeskar.travelagency.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer findCustomerById(Customer customer) {

        Optional<Customer> c = customerRepository.findById(customer.getId());

        if (c.isPresent())
            return c.get();
        else
            throw new ResourceNotFoundException("Customer", "Id", customer.getId());
    }

    public List<Customer> findAvailableDestinations() {
        return customerRepository.findAll();
    }
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer findCustomerByUsername(Customer customer){
        Optional<Customer> c = customerRepository.findByUsername(customer.getUsername());

        if (c.isPresent())
            return c.get();
        else
            throw new ResourceNotFoundException("Customer", "Username", customer.getUsername());
    }
}


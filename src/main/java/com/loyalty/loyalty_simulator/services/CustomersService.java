package com.loyalty.loyalty_simulator.services;

import com.loyalty.loyalty_simulator.interfaces.ICustomersService;
import com.loyalty.loyalty_simulator.models.Customers;
import com.loyalty.loyalty_simulator.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomersService implements ICustomersService {
    private final CustomersRepository customersRepository;
    @Autowired
    public CustomersService(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    @Override
    public void createCustomers(Customers newCustomer) {
        customersRepository.save(newCustomer);
    }
}

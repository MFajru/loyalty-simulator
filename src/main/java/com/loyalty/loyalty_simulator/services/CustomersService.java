package com.loyalty.loyalty_simulator.services;

import com.loyalty.loyalty_simulator.dto.UpdateCustomerReq;
import com.loyalty.loyalty_simulator.interfaces.ICustomersService;
import com.loyalty.loyalty_simulator.models.Customers;
import com.loyalty.loyalty_simulator.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomersService implements ICustomersService {
    private final CustomersRepository customersRepository;
    @Autowired
    public CustomersService(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    @Override
    public void createCustomer(Customers newCustomer) {
        customersRepository.save(newCustomer);
    }

    @Override
    public Customers getCustomer(String id) {
        Optional<Customers> cust = customersRepository.findById(id);
        return cust.orElse(null);
    }

    @Override
    public boolean updateCustomer(String id, UpdateCustomerReq req) {
        Optional<Customers> optCust = customersRepository.findById(id);
        if (optCust.isEmpty()) {
            return false;
        }
        Customers cust = optCust.get();
        if (req.getName() != null) cust.setName(req.getName());
        if (req.getPoint() != null) cust.setPoint(req.getPoint());
        customersRepository.save(cust);
        return true;
    }
}

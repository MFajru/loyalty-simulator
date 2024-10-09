package com.loyalty.loyalty_simulator.services;

import com.loyalty.loyalty_simulator.dto.UpdateCustomerRequest;
import com.loyalty.loyalty_simulator.exceptions.BadRequestException;
import com.loyalty.loyalty_simulator.exceptions.NotFoundException;
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
        Customers customer = customersRepository.findByCif(newCustomer.getCif());
        if (customer != null) {
            throw new BadRequestException("CIF is already exist");
        }
        customersRepository.save(newCustomer);
    }

    @Override
    public Customers getCustomer(String id) {
        Optional<Customers> cust = customersRepository.findById(id);
        if (cust.isEmpty()) {
            throw new NotFoundException("Customer with id " + id + " is not found.");
        }
        return cust.get();
    }

    @Override
    public void updateCustomer(String id, UpdateCustomerRequest req) {
        Optional<Customers> optCust = customersRepository.findById(id);
        if (optCust.isEmpty()) {
            throw new NotFoundException("Customer with id " + id + " is not found.");
        }
        Customers cust = optCust.get();
        if (req.getName() != null) cust.setName(req.getName());
        if (req.getPoint() != null) cust.setPoint(req.getPoint());
        customersRepository.save(cust);
    }
}

package com.loyalty.loyalty_simulator.interfaces;

import com.loyalty.loyalty_simulator.dto.UpdateCustomerRequest;
import com.loyalty.loyalty_simulator.models.Customers;

public interface ICustomersService {
    void createCustomer(Customers newCustomer);
    Customers getCustomer(String id);
    void updateCustomer(String id, UpdateCustomerRequest req);
}

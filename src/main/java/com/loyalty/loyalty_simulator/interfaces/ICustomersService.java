package com.loyalty.loyalty_simulator.interfaces;

import com.loyalty.loyalty_simulator.dto.UpdateCustomerReq;
import com.loyalty.loyalty_simulator.models.Customers;

public interface ICustomersService {
    void createCustomer(Customers newCustomer);
    Customers getCustomer(String id);
    boolean updateCustomer(String id, UpdateCustomerReq req);
}

package com.loyalty.loyalty_simulator.controllers;

import com.loyalty.loyalty_simulator.dto.ResponseWithoutData;
import com.loyalty.loyalty_simulator.interfaces.ICustomersService;
import com.loyalty.loyalty_simulator.models.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("customers")
public class CustomersController {
    private final ICustomersService customersService;
    @Autowired
    public CustomersController(ICustomersService customersService) {
        this.customersService = customersService;
    }

    @PostMapping(value = "/add", produces = "application/json")
    public ResponseEntity<ResponseWithoutData> addCustomer(@RequestBody Customers newCustomer) {
        ResponseWithoutData res = new ResponseWithoutData();
        customersService.createCustomers(newCustomer);
        String mess = "Successfully created customer with CIF " + newCustomer.getCif();
        res.setMessage(mess);
        return new ResponseEntity<ResponseWithoutData>(res, HttpStatus.CREATED);
    }
}

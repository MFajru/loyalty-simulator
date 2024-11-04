package com.loyalty.loyalty_simulator.controllers;

import com.loyalty.loyalty_simulator.dto.CustomerResponse;
import com.loyalty.loyalty_simulator.dto.ResponseData;
import com.loyalty.loyalty_simulator.dto.ResponseWithoutData;
import com.loyalty.loyalty_simulator.dto.UpdateCustomerRequest;
import com.loyalty.loyalty_simulator.exceptions.NotFoundException;
import com.loyalty.loyalty_simulator.interfaces.ICustomersService;
import com.loyalty.loyalty_simulator.models.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        customersService.createCustomer(newCustomer);
        String mess = "Successfully created customer with CIF " + newCustomer.getCif();
        res.setMessage(mess);
        return new ResponseEntity<ResponseWithoutData>(res, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<CustomerResponse>> getCustomer(@PathVariable String id) {
        Customers customer = customersService.getCustomer(id);
        ResponseData<CustomerResponse> res = new ResponseData<>();
        CustomerResponse custRes = new CustomerResponse(customer.getCif(), customer.getName(), customer.getPoint(), customer.getBaseEntity());
        res.setData(custRes);
        res.setMessage("Get data success!");
        return new ResponseEntity<ResponseData<CustomerResponse>>(res, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseWithoutData> updateCustomer(@RequestBody UpdateCustomerRequest req, @PathVariable String id) {
        customersService.updateCustomer(id, req);
        ResponseWithoutData res = new ResponseWithoutData();
        res.setMessage("Update successfully!");
        return new ResponseEntity<ResponseWithoutData>(res, HttpStatus.OK);
    }

}

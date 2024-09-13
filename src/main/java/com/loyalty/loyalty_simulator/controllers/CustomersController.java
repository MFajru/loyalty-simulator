package com.loyalty.loyalty_simulator.controllers;

import com.loyalty.loyalty_simulator.dto.ResponseData;
import com.loyalty.loyalty_simulator.dto.ResponseWithoutData;
import com.loyalty.loyalty_simulator.dto.UpdateCustomerReq;
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
    public ResponseEntity<ResponseData<Customers>> getCustomer(@PathVariable String id) {
        Customers customer = customersService.getCustomer(id);
        ResponseData<Customers> res = new ResponseData<>();
        if (customer == null) {
            res.setData(null);
            res.setMessage("Customer with id " + id + " is not found.");
            return new ResponseEntity<ResponseData<Customers>>(res, HttpStatus.NOT_FOUND);
        }
        res.setData(customer);
        res.setMessage("Get data success!");
        return new ResponseEntity<ResponseData<Customers>>(res, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseWithoutData> updateCustomer(@RequestBody UpdateCustomerReq req, @PathVariable String id) {
        boolean isUpdated = customersService.updateCustomer(id, req);
        ResponseWithoutData res = new ResponseWithoutData();

        if (!isUpdated) {
            res.setMessage("Customer with id " + id + " is not found.");
            return new ResponseEntity<ResponseWithoutData>(res, HttpStatus.NOT_FOUND);
        }

        res.setMessage("Update successfully!");
        return new ResponseEntity<ResponseWithoutData>(res, HttpStatus.OK);
    }
}

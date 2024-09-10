package com.loyalty.loyalty_simulator.controllers;

import com.loyalty.loyalty_simulator.dto.ResponseWithoutData;
import com.loyalty.loyalty_simulator.interfaces.ITransactionsService;
import com.loyalty.loyalty_simulator.models.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transactions")
public class TransactionsController {
    private final ITransactionsService transactionsService;
    @Autowired
    public TransactionsController(ITransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    @PostMapping(value = "/add/{cif}", produces = "application/json")
    public ResponseEntity<ResponseWithoutData> addTransaction(@RequestBody Transactions newTransaction, @PathVariable String cif) {
        ResponseWithoutData res = new ResponseWithoutData();
        boolean isCreated = transactionsService.createTransaction(newTransaction, cif);
        if (!isCreated) {
            String mess = "Customer " + cif + " not found.";
            res.setMessage(mess);
            return new ResponseEntity<ResponseWithoutData>(res, HttpStatus.NOT_FOUND);
        }
        String mess = "Successfully created transaction with Transaction Code " + newTransaction.getTranCode();
        res.setMessage(mess);
        return new ResponseEntity<ResponseWithoutData>(res, HttpStatus.CREATED);
    }
}

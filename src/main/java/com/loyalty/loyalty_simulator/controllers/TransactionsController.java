package com.loyalty.loyalty_simulator.controllers;

import com.loyalty.loyalty_simulator.dto.ResponseData;
import com.loyalty.loyalty_simulator.dto.ResponseWithoutData;
import com.loyalty.loyalty_simulator.dto.TransactionHistoryResponse;
import com.loyalty.loyalty_simulator.exceptions.NotFoundException;
import com.loyalty.loyalty_simulator.interfaces.ICustomersService;
import com.loyalty.loyalty_simulator.interfaces.ITransactionsService;
import com.loyalty.loyalty_simulator.models.Customers;
import com.loyalty.loyalty_simulator.models.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("transactions")
public class TransactionsController {
    private final ITransactionsService transactionsService;
    private final ICustomersService customersService;
    @Autowired
    public TransactionsController(ITransactionsService transactionsService, ICustomersService customersService) {
        this.transactionsService = transactionsService;
        this.customersService = customersService;
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

    @GetMapping("/{tranCode}")
    public ResponseEntity<ResponseData<Transactions>> getTransaction(@PathVariable String tranCode) {
        Transactions transactions = transactionsService.getTransaction(tranCode);
        ResponseData<Transactions> res = new ResponseData<>();
        res.setData(transactions);
        res.setMessage("Successfully getting data!");
        return new ResponseEntity<ResponseData<Transactions>>(res, HttpStatus.OK);
    }

    @GetMapping("/history/{cif}")
    public ResponseEntity<ResponseData<Set<TransactionHistoryResponse>>> getTransactionHistory(@PathVariable String cif) {
        Customers customer = customersService.getCustomer(cif);
        if (customer == null) {
            throw new NotFoundException("customer with CIF " + cif + " not found.");
        }
        Set<Transactions> transactions = customer.getTransactions();
        Set<TransactionHistoryResponse> transactionHistoryResponses = new HashSet<>();

        for (Transactions transaction: transactions) {
            TransactionHistoryResponse transactionHistoryResponse = new TransactionHistoryResponse(transaction.getTranCode(), transaction.getAmount(), transaction.getTranDate());
            transactionHistoryResponses.add(transactionHistoryResponse);
        }
        ResponseData<Set<TransactionHistoryResponse>> res = new ResponseData<>();
        res.setData(transactionHistoryResponses);
        res.setMessage("get data successful!");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}

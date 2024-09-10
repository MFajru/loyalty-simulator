package com.loyalty.loyalty_simulator.services;

import com.loyalty.loyalty_simulator.interfaces.ITransactionsService;
import com.loyalty.loyalty_simulator.models.Customers;
import com.loyalty.loyalty_simulator.models.Transactions;
import com.loyalty.loyalty_simulator.repositories.CustomersRepository;
import com.loyalty.loyalty_simulator.repositories.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionsService implements ITransactionsService {
    private final TransactionsRepository transactionsRepository;
    private final CustomersRepository customersRepository;
    @Autowired
    public TransactionsService(TransactionsRepository transactionsRepository, CustomersRepository customersRepository) {
        this.transactionsRepository = transactionsRepository;
        this.customersRepository = customersRepository;
    }

    @Override
    public boolean createTransaction(Transactions newTransaction, String cif) {
        Customers existingCust =  customersRepository.findByCif(cif);
        if (existingCust == null) {
            return false;
        }
        newTransaction.setCustomers(existingCust);
        transactionsRepository.save(newTransaction);
        return true;
    }
}

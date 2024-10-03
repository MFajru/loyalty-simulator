package com.loyalty.loyalty_simulator.services;

import com.loyalty.loyalty_simulator.exceptions.BadRequestException;
import com.loyalty.loyalty_simulator.exceptions.NotFoundException;
import com.loyalty.loyalty_simulator.interfaces.ITransactionsService;
import com.loyalty.loyalty_simulator.models.Customers;
import com.loyalty.loyalty_simulator.models.Transactions;
import com.loyalty.loyalty_simulator.repositories.CustomersRepository;
import com.loyalty.loyalty_simulator.repositories.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        Transactions transaction = transactionsRepository.findByTranCode(newTransaction.getTranCode());
        if (transaction != null) {
            throw new BadRequestException("Transaction code is already exist");
        }

        transactionsRepository.save(newTransaction);
        return true;
    }

    @Override
    public Transactions getTransaction(String tranCode) {
        Optional<Transactions> transaction = transactionsRepository.findById(tranCode);
        return transaction.orElse(null);

    }
}

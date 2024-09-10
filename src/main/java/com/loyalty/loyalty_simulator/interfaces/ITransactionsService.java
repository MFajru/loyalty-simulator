package com.loyalty.loyalty_simulator.interfaces;


import com.loyalty.loyalty_simulator.models.Transactions;

public interface ITransactionsService {
    boolean createTransaction(Transactions newTransaction, String cif);
}

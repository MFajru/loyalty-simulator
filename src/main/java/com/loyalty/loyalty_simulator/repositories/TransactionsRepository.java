package com.loyalty.loyalty_simulator.repositories;

import com.loyalty.loyalty_simulator.models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions,String> {
    Transactions findByTranCode(String tranCode);
}

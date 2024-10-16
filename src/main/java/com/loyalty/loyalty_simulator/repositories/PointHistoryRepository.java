package com.loyalty.loyalty_simulator.repositories;

import com.loyalty.loyalty_simulator.models.Customers;
import com.loyalty.loyalty_simulator.models.PointHistory;
import com.loyalty.loyalty_simulator.models.RulesAction;
import com.loyalty.loyalty_simulator.models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointHistoryRepository extends JpaRepository<PointHistory, Long> {
    List<PointHistory> findByCustomers(Customers customer);
    List<PointHistory> findByRulesActionAndTransactions(RulesAction action, Transactions transaction);
}

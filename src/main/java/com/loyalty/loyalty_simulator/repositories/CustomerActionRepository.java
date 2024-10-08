package com.loyalty.loyalty_simulator.repositories;

import com.loyalty.loyalty_simulator.models.CustomerAction;
import com.loyalty.loyalty_simulator.models.Customers;
import com.loyalty.loyalty_simulator.models.RulesAction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerActionRepository extends JpaRepository<CustomerAction, Integer> {
    CustomerAction findByCustomerAndAction(Customers customer, RulesAction rulesAction);
}

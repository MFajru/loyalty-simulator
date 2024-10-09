package com.loyalty.loyalty_simulator.repositories;

import com.loyalty.loyalty_simulator.models.CustomerAction;
import com.loyalty.loyalty_simulator.models.RulesAction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RulesActionRepository extends JpaRepository<RulesAction, Long> {
//    List<RulesAction> findByCustomerActions(CustomerAction customerAction);
}

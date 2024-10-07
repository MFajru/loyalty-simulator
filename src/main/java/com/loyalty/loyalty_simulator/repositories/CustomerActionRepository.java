package com.loyalty.loyalty_simulator.repositories;

import com.loyalty.loyalty_simulator.models.CustomerAction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerActionRepository extends JpaRepository<CustomerAction, Integer> {
}

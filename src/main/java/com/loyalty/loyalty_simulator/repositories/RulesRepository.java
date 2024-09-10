package com.loyalty.loyalty_simulator.repositories;

import com.loyalty.loyalty_simulator.models.Rules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RulesRepository extends JpaRepository<Rules, Long > {
}

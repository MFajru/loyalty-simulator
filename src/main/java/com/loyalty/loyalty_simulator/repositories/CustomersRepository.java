package com.loyalty.loyalty_simulator.repositories;

import com.loyalty.loyalty_simulator.models.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, String> {
    Customers findByCif(String cif);
}

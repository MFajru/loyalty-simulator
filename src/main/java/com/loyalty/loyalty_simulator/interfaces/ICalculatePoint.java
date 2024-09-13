package com.loyalty.loyalty_simulator.interfaces;

import com.loyalty.loyalty_simulator.dto.EarningRequest;
import com.loyalty.loyalty_simulator.models.Customers;

public interface ICalculatePoint {
    boolean earning(EarningRequest earningRequest);
}

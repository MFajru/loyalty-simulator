package com.loyalty.loyalty_simulator.interfaces;

import com.loyalty.loyalty_simulator.dto.EarningRequest;

public interface ICalculatePointService {
    boolean earning(EarningRequest earningRequest);
}

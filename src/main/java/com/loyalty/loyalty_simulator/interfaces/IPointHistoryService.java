package com.loyalty.loyalty_simulator.interfaces;

import com.loyalty.loyalty_simulator.models.PointHistory;

import java.util.List;

public interface IPointHistoryService {
    boolean addPointHistory(PointHistory pointHistory);
    List<PointHistory> getHistoryByCif(String cif);
}

package com.loyalty.loyalty_simulator.services;

import com.loyalty.loyalty_simulator.interfaces.IPointHistoryService;
import com.loyalty.loyalty_simulator.models.PointHistory;
import com.loyalty.loyalty_simulator.repositories.PointHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointHistoryService implements IPointHistoryService {
    private final PointHistoryRepository pointHistoryRepository;
    @Autowired
    public PointHistoryService(PointHistoryRepository pointHistoryRepository) {
        this.pointHistoryRepository = pointHistoryRepository;
    }

    @Override
    public boolean addPointHistory(PointHistory pointHistory) {
        pointHistoryRepository.save(pointHistory);
        return true;
    }
}

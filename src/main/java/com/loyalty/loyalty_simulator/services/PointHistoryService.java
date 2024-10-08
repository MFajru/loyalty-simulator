package com.loyalty.loyalty_simulator.services;

import com.loyalty.loyalty_simulator.exceptions.NotFoundException;
import com.loyalty.loyalty_simulator.interfaces.IPointHistoryService;
import com.loyalty.loyalty_simulator.models.Customers;
import com.loyalty.loyalty_simulator.models.PointHistory;
import com.loyalty.loyalty_simulator.repositories.PointHistoryRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointHistoryService implements IPointHistoryService {
    private final static Logger logger = LogManager.getLogger(PointHistoryService.class);

    private final PointHistoryRepository pointHistoryRepository;
    private final CustomersService customersService;
    @Autowired
    public PointHistoryService(PointHistoryRepository pointHistoryRepository, CustomersService customersService) {
        this.pointHistoryRepository = pointHistoryRepository;
        this.customersService = customersService;
    }

    @Override
    public boolean addPointHistory(PointHistory pointHistory) {
        pointHistoryRepository.save(pointHistory);
        return true;
    }

    @Override
    public List<PointHistory> getHistoryByCif(String cif) {
        Customers customer = customersService.getCustomer(cif);
        if (customer == null) {
            String mess = "Customer with cif " + cif + " not found.";
            throw new NotFoundException(mess);
        }
        List<PointHistory> pointHistory = pointHistoryRepository.findByCustomers(customer);
        if (pointHistory.isEmpty()) {
            throw new NotFoundException("Data not found");
        }
        return pointHistory;
    }
}

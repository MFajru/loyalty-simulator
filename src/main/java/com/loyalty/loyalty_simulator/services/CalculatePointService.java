package com.loyalty.loyalty_simulator.services;

import com.loyalty.loyalty_simulator.dto.ComparisonOperator;
import com.loyalty.loyalty_simulator.dto.EarningRequest;
import com.loyalty.loyalty_simulator.dto.UpdateCustomerReq;
import com.loyalty.loyalty_simulator.interfaces.ICalculatePointService;
import com.loyalty.loyalty_simulator.models.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculatePointService implements ICalculatePointService {
    private final static Logger logger = LogManager.getLogger(CalculatePointService.class);

    private final RulesService rulesService;
    private final CustomersService customersService;
    private final TransactionsService transactionsService;
    private final PointHistoryService pointHistoryService;
    @Autowired
    public CalculatePointService(RulesService rulesService, CustomersService customersService, TransactionsService transactionsService, PointHistoryService pointHistoryService) {
        this.rulesService = rulesService;
        this.customersService = customersService;
        this.transactionsService = transactionsService;
        this.pointHistoryService = pointHistoryService;
    }

    @Override
    public boolean earning(EarningRequest earningRequest) {
        Transactions transactions = transactionsService.getTransaction(earningRequest.getTranCode());
        if (transactions == null) {
            logger.warn("transaction not found");
            return false;
        }
        // next, bisa check date-nya apakah sudah h+1 atau belum

        RulesAction rulesAction = rulesService.getAction(1952L); // next, make to not static (store getAction Id in some table connected to customers)
        if (rulesAction == null) {
            logger.warn("action not found");
            return false;
        }

        ComparisonOperator operator = new ComparisonOperator();
        operator.setGreaterThan(false);
        operator.setGreaterThanEqual(false);
        operator.setEqual(false);
        operator.setLesserThan(false);
        operator.setLesserThanEqual(false);
        operator.setFullfilled(false);

        Customers cust = customersService.getCustomer(earningRequest.getCif());
        if (cust == null) {
            logger.warn("customer not found");
            return false;
        }

        if (transactions.getAmount() < rulesAction.getAmountIncrement()) {
            logger.warn("transaction amount lower than amount increment.");
            return false;
        }

        for (Rules rule: rulesAction.getRules()) {
            if (rule.getEqual() && earningRequest.getAmount().equals(rule.getComparison())) {
                operator.setFullfilled(true);
            } else if (rule.getGreaterThan() && earningRequest.getAmount() > rule.getComparison()) {
                operator.setFullfilled(true);
            } else if (rule.getLesserThan() && earningRequest.getAmount() < rule.getComparison()) {
                operator.setFullfilled(true);
            } else if (rule.getLesserThan() && rule.getEqual() && earningRequest.getAmount() <= rule.getComparison()) {
                operator.setFullfilled(true);
            } else if (rule.getGreaterThan() && rule.getEqual() && rule.getEqual() && earningRequest.getAmount() >= rule.getComparison()) {
                operator.setFullfilled(true);
            }
        }

        if (!operator.getFullfilled()) {
            logger.warn("rules not match");
            return false;
        }

        UpdateCustomerReq updateCust = new UpdateCustomerReq();
        Integer pointAcq = (transactions.getAmount() / rulesAction.getAmountIncrement()) * rulesAction.getPoint();
        updateCust.setName(cust.getName());
        updateCust.setPoint(cust.getPoint() + pointAcq);

        PointHistory pointHistory = new PointHistory();
        pointHistory.setRulesAction(rulesAction);
        pointHistory.setAmount(pointAcq);
        pointHistory.setCustomers(cust);
        pointHistory.setTransactions(transactions);
        boolean isHistoryAdded = pointHistoryService.addPointHistory(pointHistory);

        if (!isHistoryAdded) {
            logger.warn("failed to add history");
            return false;
        }

        return customersService.updateCustomer(cust.getCif(), updateCust);

    }
}

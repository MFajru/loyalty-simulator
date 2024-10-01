package com.loyalty.loyalty_simulator.services;

import com.loyalty.loyalty_simulator.dto.ComparisonOperator;
import com.loyalty.loyalty_simulator.dto.EarningRequest;
import com.loyalty.loyalty_simulator.dto.UpdateCustomerReq;
import com.loyalty.loyalty_simulator.exceptions.CustomException;
import com.loyalty.loyalty_simulator.interfaces.ICalculatePointService;
import com.loyalty.loyalty_simulator.models.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public void earning(EarningRequest earningRequest) {
        Transactions transactions = transactionsService.getTransaction(earningRequest.getTranCode());
        if (transactions == null) {
            logger.warn("transaction not found");
            throw new CustomException("transaction not found", HttpStatus.NOT_FOUND.toString());
        }
        // next, bisa check date-nya apakah sudah h+1 atau belum

        RulesAction rulesAction = rulesService.getAction(1952L); // next, make to not static (store getAction Id in some table connected to customers)
        if (rulesAction == null) {
            logger.warn("action not found");
            throw new CustomException("action not found", HttpStatus.NOT_FOUND.toString());
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
            throw new CustomException("customer not found", HttpStatus.NOT_FOUND.toString());

        }

        if (transactions.getAmount() < rulesAction.getAmountIncrement()) {
            logger.warn("transaction amount lower than amount increment");
            throw new CustomException("transaction amount lower than amount increment", HttpStatus.BAD_REQUEST.toString());

        }

        for (Rules rule : rulesAction.getRules()) {
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
            throw new CustomException("rules not match", HttpStatus.BAD_REQUEST.toString());
        }

        PointHistory pointHistory = getPointHistory(transactions, rulesAction, cust);
        boolean isHistoryAdded = pointHistoryService.addPointHistory(pointHistory);

        if (!isHistoryAdded) {
            logger.warn("failed to add history");
            throw new CustomException("failed to add history", HttpStatus.BAD_REQUEST.toString());
        }
    }

    private static PointHistory getPointHistory(Transactions transactions, RulesAction rulesAction, Customers cust) {
        UpdateCustomerReq updateCust = new UpdateCustomerReq();
        Integer pointAcq = (transactions.getAmount() / rulesAction.getAmountIncrement()) * rulesAction.getPoint();
        updateCust.setName(cust.getName());
        updateCust.setPoint(cust.getPoint() + pointAcq);

        PointHistory pointHistory = new PointHistory();
        pointHistory.setRulesAction(rulesAction);
        pointHistory.setAmount(pointAcq);
        pointHistory.setCustomers(cust);
        pointHistory.setTransactions(transactions);
        return pointHistory;
    }
}

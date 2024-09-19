package com.loyalty.loyalty_simulator.services;

import com.loyalty.loyalty_simulator.dto.ComparisonOperator;
import com.loyalty.loyalty_simulator.dto.EarningRequest;
import com.loyalty.loyalty_simulator.dto.UpdateCustomerReq;
import com.loyalty.loyalty_simulator.interfaces.ICalculatePointService;
import com.loyalty.loyalty_simulator.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculatePointService implements ICalculatePointService {
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
            System.out.println("transaction not found");
            return false;
        }
        // next, bisa check date-nya apakah sudah h+1 atau belum

        RulesAction rulesAction = rulesService.getAction(1952L); // next, make to not static (store getAction Id in some table connected to customers)
        if (rulesAction == null) {
            System.out.println("action not found");
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
            System.out.println("customer not found");
            return false;
        }

        // add condition if the amount is equal or larger than increment
        if (transactions.getAmount() < rulesAction.getAmountIncrement()) {
            System.out.println("transaction amount lower than amount increment.");
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
            System.out.println("rules not match");
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
            System.out.println("failed to add history");
            return false;
        }

        return customersService.updateCustomer(cust.getCif(), updateCust);

    }
}

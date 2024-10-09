package com.loyalty.loyalty_simulator.services;

import com.loyalty.loyalty_simulator.dto.ComparisonOperator;
import com.loyalty.loyalty_simulator.dto.EarningRequest;
import com.loyalty.loyalty_simulator.dto.UpdateCustomerRequest;
import com.loyalty.loyalty_simulator.exceptions.BadRequestException;
import com.loyalty.loyalty_simulator.exceptions.NotFoundException;
import com.loyalty.loyalty_simulator.interfaces.ICalculatePointService;
import com.loyalty.loyalty_simulator.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    public void earning(EarningRequest earningRequest) {
        Transactions transactions = transactionsService.getTransaction(earningRequest.getTranCode());
        if (transactions == null) {
            throw new NotFoundException("transaction not found");
        }

        // next, bisa check date-nya apakah sudah h+1 atau belum
        Customers cust = customersService.getCustomer(earningRequest.getCif());
        List<RulesAction> earningActions = getEarningActions(cust);

        for (RulesAction earningAction: earningActions) {
            ComparisonOperator operator = getComparisonOperator(earningRequest, earningAction, transactions);

            if (!operator.getFullfilled()) {
                throw new BadRequestException("rules not match");
            }

            PointHistory pointHistory = getPointHistory(transactions, earningAction, cust);
            boolean isHistoryAdded = pointHistoryService.addPointHistory(pointHistory);

            if (!isHistoryAdded) {
                throw new BadRequestException("failed to add history");
            }
        }
    }

    private static ComparisonOperator getComparisonOperator(EarningRequest earningRequest, RulesAction earningAction, Transactions transactions) {
        ComparisonOperator operator = new ComparisonOperator();
        operator.setGreaterThan(false);
        operator.setGreaterThanEqual(false);
        operator.setEqual(false);
        operator.setLesserThan(false);
        operator.setLesserThanEqual(false);
        operator.setFullfilled(false);

        if (transactions.getAmount() < earningAction.getAmountIncrement()) {
            throw new BadRequestException("transaction amount lower than amount increment");
        }

        for (Rules rule : earningAction.getRules()) {
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
        return operator;
    }

    private static List<RulesAction> getEarningActions(Customers cust) {
        if (cust == null) {
            throw new NotFoundException("customer not found");
        }

        Set<CustomerAction> customerActions = cust.getCustomerActions();

        // get earning rules action from customer actions
        List<RulesAction> earningActions = new ArrayList<>();
        for (CustomerAction customerAction: customerActions) {
            RulesAction ruleAction = customerAction.getAction();
            if (ruleAction.getAddition()) {
                earningActions.add(ruleAction);
            }
        }
        if (earningActions.isEmpty()) {
            throw new NotFoundException("action not found");
        }
        return earningActions;
    }

    private static PointHistory getPointHistory(Transactions transactions, RulesAction rulesAction, Customers cust) {
        UpdateCustomerRequest updateCust = new UpdateCustomerRequest();
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

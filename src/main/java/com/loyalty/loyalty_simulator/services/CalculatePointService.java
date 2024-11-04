package com.loyalty.loyalty_simulator.services;

import com.loyalty.loyalty_simulator.dto.ComparisonOperator;
import com.loyalty.loyalty_simulator.dto.EarningRequest;
import com.loyalty.loyalty_simulator.dto.UpdateCustomerRequest;
import com.loyalty.loyalty_simulator.exceptions.BadRequestException;
import com.loyalty.loyalty_simulator.exceptions.NotFoundException;
import com.loyalty.loyalty_simulator.interfaces.*;
import com.loyalty.loyalty_simulator.models.*;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CalculatePointService implements ICalculatePointService {
    private final IRulesService rulesService;
    private final ICustomersService customersService;
    private final ITransactionsService transactionsService;
    private final IPointHistoryService pointHistoryService;

    @Autowired
    public CalculatePointService(IRulesService rulesService, ICustomersService customersService, ITransactionsService transactionsService, IPointHistoryService pointHistoryService) {
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
        Customers cust = customersService.getCustomer(earningRequest.getCif());
        if (cust == null) {
            throw new NotFoundException("customer not found");
        }
        if (transactions.getCustomers() != cust) {
            throw new BadRequestException("Transaction doesn't belong to the customer.");
        }

        // next, bisa check date-nya apakah sudah h+1 atau belum
        // rules are connected with AND logic
        // do test if rule is more than one and the transcation is valid for that rules
        // if there are tactical rules, customer still got the point from basic rules
        // check date rules action apakah sudah berlaku, expired, atau tidak
        // check additional rules if exist (tid, mid is exist in transaction)
        List<CustomerAction> customerActions = rulesService.getActionByCustomer(earningRequest.getCif());
        List<RulesAction> earningActions = getEarningActions(customerActions);
        for (RulesAction earningAction: earningActions) {
            for (Rules rule: earningAction.getRules()) {
                boolean isRulesFullfilled = isRulesFullfilled(earningRequest, rule);
                if (!isRulesFullfilled) {
                    throw new BadRequestException("Rules not match.");
                }
            }
            UpdateCustomerRequest updateCust = new UpdateCustomerRequest();
            Integer pointAcq = (transactions.getAmount() / earningAction.getAmountIncrement()) * earningAction.getPoint();
            updateCust.setPoint(cust.getPoint() + pointAcq);
            customersService.updateCustomer(cust.getCif(), updateCust);

            PointHistory pointHistory = new PointHistory();
            pointHistory.setRulesAction(earningAction);
            pointHistory.setAmount(pointAcq);
            pointHistory.setCustomers(cust);
            pointHistory.setTransactions(transactions);

            pointHistoryService.addPointHistory(pointHistory);
        }
    }

    private static boolean isRulesFullfilled(EarningRequest earningRequest, Rules rule) {
        boolean isRulesFullfilled = false;
        if (rule.getEqual() && earningRequest.getAmount().equals(rule.getComparison())) {
            isRulesFullfilled = true;
        } else if (rule.getGreaterThan() && earningRequest.getAmount() > rule.getComparison()) {
            isRulesFullfilled = true;
        } else if (rule.getLesserThan() && earningRequest.getAmount() < rule.getComparison()) {
            isRulesFullfilled = true;
        } else if (rule.getLesserThan() && rule.getEqual() && earningRequest.getAmount() <= rule.getComparison()) {
            isRulesFullfilled = true;
        } else if (rule.getGreaterThan() && rule.getEqual() && rule.getEqual() && earningRequest.getAmount() >= rule.getComparison()) {
            isRulesFullfilled = true;
        }
        return isRulesFullfilled;
    }

    private static List<RulesAction> getEarningActions(List<CustomerAction> customerActions) {
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
}

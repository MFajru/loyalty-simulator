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
        // bisa insert ke table jika transaksi udah ada tapi dengan action yang berbeda
        // untuk rules, make the new rules is logically related to the existing rules. Ex: if existing rules is lower than 50000, you cannot make rules with equal to
        // rules are connected with AND logic
        // do test if rule is more than one and the transcation is valid for that rules
        List<CustomerAction> customerActions = rulesService.getActionByCustomer(earningRequest.getCif());
        List<RulesAction> earningActions = getEarningActions(customerActions);
        boolean isFullfilledRules = false;
        for (RulesAction earningAction: earningActions) {
            for (Rules rule: earningAction.getRules()) {
                if (rule.getEqual() && earningRequest.getAmount().equals(rule.getComparison())) {
                    isFullfilledRules = true;
                } else if (rule.getGreaterThan() && earningRequest.getAmount() > rule.getComparison()) {
                    isFullfilledRules = true;
                } else if (rule.getLesserThan() && earningRequest.getAmount() < rule.getComparison()) {
                    isFullfilledRules = true;
                } else if (rule.getLesserThan() && rule.getEqual() && earningRequest.getAmount() <= rule.getComparison()) {
                    isFullfilledRules = true;
                } else if (rule.getGreaterThan() && rule.getEqual() && rule.getEqual() && earningRequest.getAmount() >= rule.getComparison()) {
                    isFullfilledRules = true;
                }
                if (!isFullfilledRules) {
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

            // cek apakah cif ada tapi action tidak ada
            pointHistoryService.addPointHistory(pointHistory);
        }
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

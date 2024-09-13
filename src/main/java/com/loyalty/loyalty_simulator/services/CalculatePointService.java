package com.loyalty.loyalty_simulator.services;

import com.loyalty.loyalty_simulator.dto.ComparisonOperator;
import com.loyalty.loyalty_simulator.dto.EarningRequest;
import com.loyalty.loyalty_simulator.interfaces.ICalculatePoint;
import com.loyalty.loyalty_simulator.models.Customers;
import com.loyalty.loyalty_simulator.models.Rules;
import com.loyalty.loyalty_simulator.models.RulesAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculatePointService implements ICalculatePoint {
//    private final RulesActionRepository rulesAction;
    private final RulesService rulesService;
    private final CustomersService customersService;
    @Autowired
    public CalculatePointService(RulesService rulesService, CustomersService customersService) {
        this.rulesService = rulesService;
        this.customersService = customersService;
    }

    @Override
    public boolean earning(EarningRequest earningRequest) {
        RulesAction rulesAction = rulesService.getAction(1003L); // next, make to not static (store getAction Id in some table connected to customers)
        if (rulesAction == null) {
            return false;
        }

        ComparisonOperator operator = new ComparisonOperator();
        operator.setGreaterThan(false);
        operator.setGreaterThanEqual(false);
        operator.setEqual(false);
        operator.setLesserThan(false);
        operator.setLesserThanEqual(false);
        Customers cust = customersService.getCustomer(earningRequest.getCif());

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
            } else {
                operator.setFullfilled(false);
            }
        }

        if (!operator.getFullfilled()) {
            return false;
        }

        Integer tempPoint = cust.getPoint();
        tempPoint += rulesAction.getPoint();
        //update point in customer. make service for update data.

        return true;

    }
}

package com.loyalty.loyalty_simulator.services;

import com.loyalty.loyalty_simulator.dto.ComparisonOperator;
import com.loyalty.loyalty_simulator.dto.EarningRequest;
import com.loyalty.loyalty_simulator.dto.UpdateCustomerReq;
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
        //verify trancode exist or not
        RulesAction rulesAction = rulesService.getAction(1003L); // next, make to not static (store getAction Id in some table connected to customers)
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

        Customers cust = customersService.getCustomer(earningRequest.getCif());
        if (cust == null) {
            System.out.println("customer not found");
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
            } else {
                operator.setFullfilled(false);
            }
        }

        if (!operator.getFullfilled()) {
            System.out.println("rules not match");
            return false;
        }

        UpdateCustomerReq updateCust = new UpdateCustomerReq();
        updateCust.setName(cust.getName());
        updateCust.setPoint(cust.getPoint() + rulesAction.getPoint());

        return customersService.updateCustomer(cust.getCif(), updateCust);

    }
}

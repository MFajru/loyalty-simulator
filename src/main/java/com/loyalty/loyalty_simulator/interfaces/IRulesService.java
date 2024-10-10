package com.loyalty.loyalty_simulator.interfaces;

import com.loyalty.loyalty_simulator.models.CustomerAction;
import com.loyalty.loyalty_simulator.models.Rules;
import com.loyalty.loyalty_simulator.models.RulesAction;

import java.util.List;

public interface IRulesService {
    boolean createRule(Rules newRule);
    void createRulesAction(RulesAction newAction);
    Rules getRule(Long id);
    RulesAction getAction(Long id);
    void addActionToCustomer(String cif, Long rulesActionId);
    List<CustomerAction> getActionByCustomer(String cif);

}

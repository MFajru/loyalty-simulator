package com.loyalty.loyalty_simulator.interfaces;

import com.loyalty.loyalty_simulator.dto.AddActionToCustomerReq;
import com.loyalty.loyalty_simulator.models.Rules;
import com.loyalty.loyalty_simulator.models.RulesAction;

public interface IRulesService {
    boolean createRule(Rules newRule);
    boolean createRulesAction(RulesAction newAction);
    Rules getRule(Long id);
    RulesAction getAction(Long id);
    void addActionToCustomer(String cif, Long rulesActionId);

}

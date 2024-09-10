package com.loyalty.loyalty_simulator.interfaces;

import com.loyalty.loyalty_simulator.models.Rules;
import com.loyalty.loyalty_simulator.models.RulesAction;

public interface IRulesService {
    boolean createRules(Rules newRules);
    boolean createRulesAction(RulesAction newAction);
    Rules getRule(Long id);

}

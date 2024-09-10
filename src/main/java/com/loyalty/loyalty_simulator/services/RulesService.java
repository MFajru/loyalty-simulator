package com.loyalty.loyalty_simulator.services;

import com.loyalty.loyalty_simulator.interfaces.IRulesService;
import com.loyalty.loyalty_simulator.models.Rules;
import com.loyalty.loyalty_simulator.models.RulesAction;
import com.loyalty.loyalty_simulator.repositories.RulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RulesService implements IRulesService {
    private final RulesRepository rulesRepository;
    @Autowired
    public RulesService(RulesRepository rulesRepository) {
        this.rulesRepository = rulesRepository;
    }

    @Override
    public boolean createRules(Rules newRules) {
        rulesRepository.save(newRules);
        return true;
    }
}

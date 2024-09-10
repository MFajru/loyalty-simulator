package com.loyalty.loyalty_simulator.services;

import com.loyalty.loyalty_simulator.interfaces.IRulesService;
import com.loyalty.loyalty_simulator.models.Rules;
import com.loyalty.loyalty_simulator.models.RulesAction;
import com.loyalty.loyalty_simulator.repositories.RulesActionRepository;
import com.loyalty.loyalty_simulator.repositories.RulesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Optional;

@Service
public class RulesService implements IRulesService {
    private final RulesRepository rulesRepository;
    private final RulesActionRepository rulesActionRepository;
    @Autowired
    public RulesService(RulesRepository rulesRepository, RulesActionRepository rulesActionRepository) {
        this.rulesRepository = rulesRepository;
        this.rulesActionRepository = rulesActionRepository;
    }

    @Override
    public boolean createRules(Rules newRules) {
        rulesRepository.save(newRules);
        return true;
    }

    @Override
    public boolean createRulesAction(RulesAction newAction) {
        rulesActionRepository.save(newAction);
        return true;
    }

    @Override
    public Rules getRule(Long id) {
        Optional<Rules> gottenRule = rulesRepository.findById(id);
        return gottenRule.orElseThrow(()->new EntityNotFoundException("Rules with id " + id + " not found."));
    }
}

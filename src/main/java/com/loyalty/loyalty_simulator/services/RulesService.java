package com.loyalty.loyalty_simulator.services;

import com.loyalty.loyalty_simulator.dto.AddActionToCustomerReq;
import com.loyalty.loyalty_simulator.exceptions.NotFoundException;
import com.loyalty.loyalty_simulator.interfaces.IRulesService;
import com.loyalty.loyalty_simulator.models.CustomerAction;
import com.loyalty.loyalty_simulator.models.Customers;
import com.loyalty.loyalty_simulator.models.Rules;
import com.loyalty.loyalty_simulator.models.RulesAction;
import com.loyalty.loyalty_simulator.repositories.CustomerActionRepository;
import com.loyalty.loyalty_simulator.repositories.RulesActionRepository;
import com.loyalty.loyalty_simulator.repositories.RulesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RulesService implements IRulesService {
    private final RulesRepository rulesRepository;
    private final RulesActionRepository rulesActionRepository;
    private final CustomerActionRepository customerActionRepository;
    private final CustomersService customersService;
    @Autowired
    public RulesService(RulesRepository rulesRepository, RulesActionRepository rulesActionRepository, CustomerActionRepository customerActionRepository, CustomersService customersService) {
        this.rulesRepository = rulesRepository;
        this.rulesActionRepository = rulesActionRepository;
        this.customerActionRepository = customerActionRepository;
        this.customersService = customersService;
    }

    @Override
    public boolean createRule(Rules newRule) {
        rulesRepository.save(newRule);
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

    @Override
    public RulesAction getAction(Long id) {
        Optional<RulesAction> action = rulesActionRepository.findById(id);
        return action.orElse(null);
    }

    @Override
    public void addActionToCustomer(AddActionToCustomerReq req) {
        Customers existCust = customersService.getCustomer(req.getCif());
        if (existCust == null) {
            throw new NotFoundException("Customer with CIF " + req.getCif() + " not found.");
        }

        RulesAction existRulesAct = getAction(req.getRulesActionId());
        if (existRulesAct == null) {
            throw new NotFoundException("Rules action with ID " + req.getRulesActionId() + " not found.");
        }
        CustomerAction newCustAct = new CustomerAction();
        newCustAct.setCustomer(existCust);
        newCustAct.setAction(existRulesAct);

        customerActionRepository.save(newCustAct);
    }
}

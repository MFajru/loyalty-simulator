package com.loyalty.loyalty_simulator.services;

import com.loyalty.loyalty_simulator.exceptions.BadRequestException;
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
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
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

    // untuk rules, make the new rules is logically related to the existing rules. Ex: if existing rules is lower than 50000, you cannot make rules with equal to
    @Override
    public void createRule(Rules newRule) {
        try {
            rulesRepository.save(newRule);
        } catch (Exception e) {
            throw new ServiceException("Unexpected error occur, " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteRule(Long id) {
        Optional<Rules> optRule = rulesRepository.findById(id);
        if (optRule.isEmpty()) {
            throw new NotFoundException("Rule with ID " + id + " not found.");
        }
        try {
            Rules rule = optRule.get();
            rule.setAction(null);
            rulesRepository.save(rule);
            rulesRepository.delete(rule);
        } catch (Exception e) {
            throw new ServiceException("Unexpected error occur, " + e.getMessage(), e);
        }
    }

    @Override
    public void createRulesAction(RulesAction newAction) {
        try {
            rulesActionRepository.save(newAction);
        } catch (Exception e) {
            throw new ServiceException("Unexpected error occur, " + e.getMessage(), e);
        }
    }

    @Override
    public Rules getRule(Long id) {
        Optional<Rules> gottenRule = rulesRepository.findById(id);
        if (gottenRule.isEmpty()) {
            throw new NotFoundException("Rules with id " + id + " not found.");
        }
        return gottenRule.get();
    }

    @Override
    public RulesAction getAction(Long id) {
        Optional<RulesAction> action = rulesActionRepository.findById(id);
        if (action.isEmpty()) {
            throw new NotFoundException("Data with id " + id + " not found.");
        }
        return action.get();
    }

    @Override
    public void addActionToCustomer(String cif, Long rulesActionId) {
        Customers existCust = customersService.getCustomer(cif);
        if (existCust == null) {
            throw new NotFoundException("Customer with CIF " + cif + " not found.");
        }

        RulesAction existRulesAct = getAction(rulesActionId);
        if (existRulesAct == null) {
            throw new NotFoundException("Rules action with ID " + rulesActionId + " not found.");
        }

        CustomerAction existCustAct = customerActionRepository.findByCustomerAndAction(existCust, existRulesAct);
        if (existCustAct != null) {
            throw new BadRequestException("Customer " + cif + " with rules action " + rulesActionId + " is already exist");
        }
        CustomerAction newCustAct = new CustomerAction();
        newCustAct.setCustomer(existCust);
        newCustAct.setAction(existRulesAct);

        customerActionRepository.save(newCustAct);
    }

    @Override
    public List<CustomerAction> getActionByCustomer(String cif) {
        List<CustomerAction> customerActions = customerActionRepository.findByCustomer_Cif(cif);
        if (customerActions.isEmpty()) {
            throw new NotFoundException("Customer with CIF " + cif + " is either not exist or doesn't registered to any rules");
        }
        return customerActions;
    }
}

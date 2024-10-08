package com.loyalty.loyalty_simulator.controllers;

import com.loyalty.loyalty_simulator.dto.*;
import com.loyalty.loyalty_simulator.interfaces.IRulesService;
import com.loyalty.loyalty_simulator.models.Rules;
import com.loyalty.loyalty_simulator.models.RulesAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;

@RestController
@RequestMapping("rules")
public class RulesController {
    private final IRulesService rulesService;
    @Autowired
    public RulesController(IRulesService rulesService) {
        this.rulesService = rulesService;
    }

    @PostMapping("/add-action")
    public ResponseEntity<ResponseWithoutData> addAction(@RequestBody AddActionRequest actionRequest) throws IllegalAccessException {
        for (Field field: actionRequest.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.get(actionRequest) == null && field.getType().equals(Boolean.class)) {
                field.set(actionRequest, false);
            }
        }
        RulesAction newAction = new RulesAction();
        newAction.setPoint(actionRequest.getPoint());
        newAction.setDeduction(actionRequest.getDeduction());
        newAction.setAddition(actionRequest.getAddition());
        newAction.setAmountIncrement(actionRequest.getAmountIncrement());

        ResponseWithoutData res = new ResponseWithoutData();
        boolean isCreated = rulesService.createRulesAction(newAction);
        if (!isCreated) {
            res.setMessage("Failed to create data!");
            return new ResponseEntity<ResponseWithoutData>(res, HttpStatus.BAD_REQUEST);
        }

        res.setMessage("Successfully create data!");
        return new ResponseEntity<ResponseWithoutData>(res, HttpStatus.CREATED);
    }

    @GetMapping("/action/{id}")
    public ResponseEntity<ResponseData<RulesAction>> getAction(@PathVariable Long id) {
        RulesAction action = rulesService.getAction(id);
        ResponseData<RulesAction> res = new ResponseData<>();
        if (action == null) {
            res.setMessage("Data with id " + id + " not found.");
            res.setData(null);
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        }
        res.setData(action);
        res.setMessage("Successfully getting data!");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/add-rule/{actionId}")
    public ResponseEntity<ResponseWithoutData> addRules(@RequestBody @Validated List<AddRulesRequest> rulesRequest, @PathVariable Long actionId) throws IllegalAccessException {
        RulesAction existingAct = rulesService.getAction(actionId);
        ResponseWithoutData res = new ResponseWithoutData();
        if (existingAct == null) {
            res.setMessage("Data with id " + actionId + " not found.");
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        }
        for(AddRulesRequest ruleRequest: rulesRequest) {
            for (Field field: ruleRequest.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.get(ruleRequest) == null && field.getType().equals(Boolean.class)) {
                    field.set(ruleRequest, false);
                }
            }
            Rules newRule = new Rules();
            newRule.setComparison(ruleRequest.getComparison());
            newRule.setEqual(ruleRequest.getEqual());
            newRule.setGreaterThan(ruleRequest.getGreaterThan());
            newRule.setLesserThan(ruleRequest.getLesserThan());
            newRule.setAction(existingAct);
            boolean isCreated = rulesService.createRule(newRule);
            if (!isCreated) {
                res.setMessage("Failed to create data!");
                return new ResponseEntity<ResponseWithoutData>(res, HttpStatus.BAD_REQUEST);
            }
        }

        res.setMessage("Successfully create data!");
        return new ResponseEntity<ResponseWithoutData>(res, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<RuleResponse>> getRule(@PathVariable Long id) {
        Rules rule = rulesService.getRule(id);
        RuleResponse ruleResponse = new RuleResponse();
        ruleResponse.setId(rule.getId());
        ruleResponse.setComparison(rule.getComparison());
        ruleResponse.setEqual(rule.getEqual());
        ruleResponse.setGreaterThan(rule.getGreaterThan());
        ruleResponse.setLessThan(rule.getLesserThan());

        RuleActionResponse ruleActionResponse =  new RuleActionResponse();
        ruleActionResponse.setAddition(rule.getAction().getAddition());
        ruleActionResponse.setDeduction(rule.getAction().getDeduction());
        ruleActionResponse.setId(rule.getAction().getId());
        ruleActionResponse.setPoint(rule.getAction().getPoint());
        ruleResponse.setAction(ruleActionResponse);

        ResponseData<RuleResponse> res = new ResponseData<>();
        res.setData(ruleResponse);
        res.setMessage("Get data success!");
        return new ResponseEntity<ResponseData<RuleResponse>>(res, HttpStatus.OK);
    }

    @PostMapping("add-to-customer")
    public ResponseEntity<ResponseWithoutData> addActionToCustomer(@RequestBody AddActionToCustomerReq req) {
        rulesService.addActionToCustomer(req.getCif(), req.getRulesActionId());
        ResponseWithoutData res = new ResponseWithoutData();
        res.setMessage("Successfully adding data!");
        return new ResponseEntity<ResponseWithoutData>(res, HttpStatus.CREATED);
    }
}

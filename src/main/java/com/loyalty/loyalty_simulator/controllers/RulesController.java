package com.loyalty.loyalty_simulator.controllers;

import com.loyalty.loyalty_simulator.dto.AddRulesRequest;
import com.loyalty.loyalty_simulator.dto.ResponseWithoutData;
import com.loyalty.loyalty_simulator.interfaces.IRulesService;
import com.loyalty.loyalty_simulator.models.Rules;
import com.loyalty.loyalty_simulator.models.RulesAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;

@RestController
@RequestMapping("rules")
public class RulesController {
    private final IRulesService rulesService;
    @Autowired
    public RulesController(IRulesService rulesService) {
        this.rulesService = rulesService;
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseWithoutData> addRules(@RequestBody AddRulesRequest rulesRequest) throws IllegalAccessException {
        for (Field field: rulesRequest.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.get(rulesRequest) == null && field.getType().equals(Boolean.class)) {
                field.set(rulesRequest, false);
            }
        }
        Rules newRules = mappingRules(rulesRequest);

        ResponseWithoutData res = new ResponseWithoutData();
        boolean isCreated = rulesService.createRules(newRules);
        if (!isCreated) {
            res.setMessage("Failed to create data!");
            return new ResponseEntity<ResponseWithoutData>(res, HttpStatus.BAD_REQUEST);
        }

        res.setMessage("Successfully create data!");
        return new ResponseEntity<ResponseWithoutData>(res, HttpStatus.CREATED);


    }

    private static Rules mappingRules(AddRulesRequest rulesRequest) {
        RulesAction newAction = new RulesAction();
        newAction.setAddition(rulesRequest.getAddition());
        newAction.setDeduction(rulesRequest.getDeduction());
        newAction.setPoint(rulesRequest.getPoint());

        Rules newRules = new Rules();
        newRules.setComparison(rulesRequest.getComparison());
        newRules.setEqual(rulesRequest.getEqual());
        newRules.setGreaterThan(rulesRequest.getGreaterThan());
        newRules.setLessThan(rulesRequest.getLessThan());
        newRules.setAction(newAction);
        return newRules;
    }
}

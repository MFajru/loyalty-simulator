package com.loyalty.loyalty_simulator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.loyalty.loyalty_simulator.models.Rules;

import java.util.Set;

public class ActionByCustomerResponse {
    private Long id;
    private Boolean addition;
    private Boolean deduction;
    private Integer point;
    @JsonProperty("amount_increment")
    private Integer amountIncrement;
    private Set<Rules> rules;

    public ActionByCustomerResponse(Long id, Boolean addition, Boolean deduction, Integer point, Integer amountIncrement, Set<Rules> rules) {
        this.id = id;
        this.addition = addition;
        this.deduction = deduction;
        this.point = point;
        this.amountIncrement = amountIncrement;
        this.rules = rules;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAddition() {
        return addition;
    }

    public void setAddition(Boolean addition) {
        this.addition = addition;
    }

    public Boolean getDeduction() {
        return deduction;
    }

    public void setDeduction(Boolean deduction) {
        this.deduction = deduction;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getAmountIncrement() {
        return amountIncrement;
    }

    public void setAmountIncrement(Integer amountIncrement) {
        this.amountIncrement = amountIncrement;
    }

    public Set<Rules> getRules() {
        return rules;
    }

    public void setRules(Set<Rules> rules) {
        this.rules = rules;
    }
}

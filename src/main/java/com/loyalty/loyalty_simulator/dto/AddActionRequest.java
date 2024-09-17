package com.loyalty.loyalty_simulator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddActionRequest {
    private Boolean addition;
    private Boolean deduction;
    private Integer point;
    @JsonProperty("amount_increment")
    private Integer amountIncrement;

    public Integer getAmountIncrement() {
        return amountIncrement;
    }

    public void setAmountIncrement(Integer amountIncrement) {
        this.amountIncrement = amountIncrement;
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
}

package com.loyalty.loyalty_simulator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddRulesRequest {
    @JsonProperty("less_than")
    private Boolean lessThan;
    @JsonProperty("greater_than")
    private Boolean greaterThan;
    private Boolean equal;
    private Integer comparison;
    private Boolean addition;
    private Boolean deduction;
    private Integer point;

    public Boolean getLessThan() {
        return lessThan;
    }

    public void setLessThan(Boolean lessThan) {
        this.lessThan = lessThan;
    }

    public Boolean getGreaterThan() {
        return greaterThan;
    }

    public void setGreaterThan(Boolean greaterThan) {
        this.greaterThan = greaterThan;
    }

    public Boolean getEqual() {
        return equal;
    }

    public void setEqual(Boolean equal) {
        this.equal = equal;
    }

    public Integer getComparison() {
        return comparison;
    }

    public void setComparison(Integer comparison) {
        this.comparison = comparison;
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

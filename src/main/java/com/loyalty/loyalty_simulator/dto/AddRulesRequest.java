package com.loyalty.loyalty_simulator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.loyalty.loyalty_simulator.models.RulesAction;

public class AddRulesRequest {
    @JsonProperty("lesser_than")
    private Boolean lesserThan;
    @JsonProperty("greater_than")
    private Boolean greaterThan;
    private Boolean equal;
    private Integer comparison;
    private RulesAction rulesAction;

    public RulesAction getRulesAction() {
        return rulesAction;
    }

    public void setRulesAction(RulesAction rulesAction) {
        this.rulesAction = rulesAction;
    }

    public Boolean getLesserThan() {
        return lesserThan;
    }

    public void setLesserThan(Boolean lesserThan) {
        this.lesserThan = lesserThan;
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


}

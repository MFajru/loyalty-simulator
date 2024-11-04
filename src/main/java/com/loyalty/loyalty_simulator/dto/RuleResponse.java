package com.loyalty.loyalty_simulator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.loyalty.loyalty_simulator.models.AdditionalRules;
import com.loyalty.loyalty_simulator.models.BaseEntity;
import jakarta.persistence.Embedded;

import java.util.Set;

@JsonPropertyOrder({
        "id",
        "less_than",
        "greater_than",
        "equal",
        "comparison",
        "additional_rules",
        "action",
        "base_entity"
})
public class RuleResponse {
    private Long id;
    @JsonProperty("less_than")
    private Boolean lessThan;
    @JsonProperty("greater_than")
    private Boolean greaterThan;
    private Boolean equal;
    private Integer comparison;
    private RuleActionResponse action;
    @JsonProperty("additional_rules")
    private Set<AdditionalRules> additionalRules;
    @Embedded
    @JsonUnwrapped
    @JsonProperty("base_entity")
    private BaseEntity baseEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public RuleActionResponse getAction() {
        return action;
    }

    public void setAction(RuleActionResponse action) {
        this.action = action;
    }

    public Set<AdditionalRules> getAdditionalRules() {
        return additionalRules;
    }

    public void setAdditionalRules(Set<AdditionalRules> additionalRules) {
        this.additionalRules = additionalRules;
    }

    public BaseEntity getBaseEntity() {
        return baseEntity;
    }

    public void setBaseEntity(BaseEntity baseEntity) {
        this.baseEntity = baseEntity;
    }
}

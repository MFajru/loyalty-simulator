package com.loyalty.loyalty_simulator.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "zzz_rules")
public class Rules {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("lesser_than")
    private Boolean lesserThan;
    @JsonProperty("greater_than")
    private Boolean greaterThan;
    private Boolean equal;
    private Integer comparison;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="action_id", referencedColumnName = "id")
    @JsonBackReference("rulesAction")
    private RulesAction action;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "rule_id")
    private Set<AdditionalRules> additionalRules = new HashSet<>();

    @Embedded
    private BaseEntity baseEntity;

    public Rules(Boolean lesserThan, Boolean greaterThan, Boolean equal, Integer comparison, RulesAction action, Set<AdditionalRules> additionalRules) {
        this.lesserThan = lesserThan;
        this.greaterThan = greaterThan;
        this.equal = equal;
        this.comparison = comparison;
        this.action = action;
        this.additionalRules = additionalRules;
    }

    public Rules() {

    }

    public RulesAction getAction() {
        return action;
    }

    public void setAction(RulesAction action) {
        this.action = action;
    }

    public Integer getComparison() {
        return comparison;
    }

    public void setComparison(Integer comparison) {
        this.comparison = comparison;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getLesserThan() {
        return lesserThan;
    }

    public void setLesserThan(Boolean lessThan) {
        this.lesserThan = lessThan;
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

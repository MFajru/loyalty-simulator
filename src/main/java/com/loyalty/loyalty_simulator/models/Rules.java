package com.loyalty.loyalty_simulator.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "zzz_rules")
public class Rules {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("less_than")
    private Boolean lessThan;
    @JsonProperty("greater_than")
    private Boolean greaterThan;
    private Boolean equal;
    private Integer comparison;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="action_id", referencedColumnName = "id")
    @JsonBackReference
    private RulesAction action;

    @OneToMany(mappedBy = "rules")
    @JsonIgnoreProperties("rules")
    private Set<PointHistoryRules> pointHistoryRules = new HashSet<>();

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

    public Set<PointHistoryRules> getPointHistoryRules() {
        return pointHistoryRules;
    }

    public void setPointHistoryRules(Set<PointHistoryRules> pointHistoryRules) {
        this.pointHistoryRules = pointHistoryRules;
    }
}

package com.loyalty.loyalty_simulator.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "zzz_rules_action")
public class RulesAction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Boolean addition;
    private Boolean deduction;
    private Integer point;
    @JsonProperty("amount_increment")
    private Integer amountIncrement;

    @OneToMany(mappedBy = "action")
    @JsonManagedReference
    private Set<Rules> rules = new HashSet<>();

    @OneToMany(mappedBy = "rulesAction")
    @JsonIgnore
    private List<PointHistory> pointHistory = new ArrayList<>();

    public List<PointHistory> getPointHistory() {
        return pointHistory;
    }

    public void setPointHistory(List<PointHistory> pointHistory) {
        this.pointHistory = pointHistory;
    }

    public Integer getAmountIncrement() {
        return amountIncrement;
    }

    public void setAmountIncrement(Integer amountIncrement) {
        this.amountIncrement = amountIncrement;
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

    public Set<Rules> getRules() {
        return rules;
    }

    public void setRules(Set<Rules> rules) {
        this.rules = rules;
    }
}

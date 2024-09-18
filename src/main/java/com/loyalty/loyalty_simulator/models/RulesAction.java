package com.loyalty.loyalty_simulator.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "zzz_rules_action")
public class RulesAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean addition;
    private Boolean deduction;
    private Integer point;
    @JsonProperty("amount_increment")
    private Integer amountIncrement;

    @OneToMany(mappedBy = "action")
    @JsonManagedReference
    private Set<Rules> rules = new HashSet<>();

    @OneToOne(mappedBy = "rulesAction")
    private PointHistory pointHistory;

    public PointHistory getPointHistory() {
        return pointHistory;
    }

    public void setPointHistory(PointHistory pointHistory) {
        this.pointHistory = pointHistory;
    }

    //    @OneToMany(mappedBy = "action")
//    @JsonIgnoreProperties("action")
//    @JsonProperty("point_history_rules")
//    private Set<PointHistoryRules> pointHistoryRules = new HashSet<>();

    public Integer getAmountIncrement() {
        return amountIncrement;
    }

    public void setAmountIncrement(Integer amountIncrement) {
        this.amountIncrement = amountIncrement;
    }

//    public Set<PointHistoryRules> getPointHistoryRules() {
//
//        return pointHistoryRules;
//    }
//
//    public void setPointHistoryRules(Set<PointHistoryRules> pointHistoryRules) {
//        this.pointHistoryRules = pointHistoryRules;
//    }

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

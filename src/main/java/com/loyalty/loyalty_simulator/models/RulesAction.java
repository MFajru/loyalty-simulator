package com.loyalty.loyalty_simulator.models;

import jakarta.persistence.*;

import java.util.HashSet;
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

    @OneToMany(mappedBy = "action")
    private Set<Rules> rules = new HashSet<>();

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

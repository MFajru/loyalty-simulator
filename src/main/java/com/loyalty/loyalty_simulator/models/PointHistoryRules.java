package com.loyalty.loyalty_simulator.models;

import jakarta.persistence.*;

@Entity
@Table(name = "zzz_point_history_rules")
public class PointHistoryRules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pt_his_id", referencedColumnName = "id")
    private PointHistory pointHistory;

    @ManyToOne
    @JoinColumn(name = "rule_id", referencedColumnName = "id")
    private Rules rules;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PointHistory getPointHistory() {
        return pointHistory;
    }

    public void setPointHistory(PointHistory pointHistory) {
        this.pointHistory = pointHistory;
    }

    public Rules getRules() {
        return rules;
    }

    public void setRules(Rules rules) {
        this.rules = rules;
    }
}

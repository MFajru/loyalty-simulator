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
    @JoinColumn(name = "action_id", referencedColumnName = "id")
    private RulesAction action;

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

    public RulesAction getRules() {
        return action;
    }

    public void setRules(RulesAction action) {
        this.action = action;
    }
}

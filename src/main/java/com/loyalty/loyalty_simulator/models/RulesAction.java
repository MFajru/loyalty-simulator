package com.loyalty.loyalty_simulator.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;
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
    private Integer priority;
    @JsonProperty("start_date")
    private LocalDateTime startDate;
    @JsonProperty("end_date")
    private LocalDateTime endDate;
    @JsonProperty("max_points_earn")
    private Integer maxPointsEarn;

    @OneToMany(mappedBy = "action")
    @JsonManagedReference("rulesAction")
    private Set<Rules> rules = new HashSet<>();

    @OneToMany(mappedBy = "rulesAction")
    @JsonIgnore
    private List<PointHistory> pointHistory = new ArrayList<>();

    @OneToMany(mappedBy = "action")
    private Set<CustomerAction> customerActions = new HashSet<>();

    @Embedded
    private BaseEntity baseEntity;

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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Integer getMaxPointsEarn() {
        return maxPointsEarn;
    }

    public void setMaxPointsEarn(Integer maxPointsEarn) {
        this.maxPointsEarn = maxPointsEarn;
    }

    public Set<CustomerAction> getCustomerActions() {
        return customerActions;
    }

    public void setCustomerActions(Set<CustomerAction> customerActions) {
        this.customerActions = customerActions;
    }

    public BaseEntity getBaseEntity() {
        return baseEntity;
    }

    public void setBaseEntity(BaseEntity baseEntity) {
        this.baseEntity = baseEntity;
    }
}

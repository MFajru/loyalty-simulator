package com.loyalty.loyalty_simulator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.loyalty.loyalty_simulator.models.BaseEntity;
import com.loyalty.loyalty_simulator.models.Rules;
import jakarta.persistence.Embedded;

import java.time.LocalDateTime;
import java.util.Set;

@JsonPropertyOrder({
        "id",
        "start_date",
        "end_date",
        "addition",
        "deduction",
        "priority",
        "point",
        "max_points_earn",
        "amount_increment",
        "rules"
})
public class ActionByCustomerResponse {
    private Long id;
    private Boolean addition;
    private Boolean deduction;
    private Integer point;
    private Integer priority;
    @JsonProperty("max_points_earn")
    private Integer maxPointsEarn;
    @JsonProperty("amount_increment")
    private Integer amountIncrement;
    @JsonProperty("start_date")
    private LocalDateTime startDate;
    @JsonProperty("end_date")
    private LocalDateTime endDate;
    private Set<Rules> rules;
    @Embedded
    @JsonUnwrapped
    private BaseEntity baseEntity;

    public ActionByCustomerResponse(Long id, Boolean addition, Boolean deduction, Integer point, Integer priority, Integer maxPointsEarn, Integer amountIncrement, LocalDateTime startDate, LocalDateTime endDate, Set<Rules> rules, BaseEntity baseEntity) {
        this.id = id;
        this.addition = addition;
        this.deduction = deduction;
        this.point = point;
        this.priority = priority;
        this.maxPointsEarn = maxPointsEarn;
        this.amountIncrement = amountIncrement;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rules = rules;
        this.baseEntity = baseEntity;
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

    public Integer getAmountIncrement() {
        return amountIncrement;
    }

    public void setAmountIncrement(Integer amountIncrement) {
        this.amountIncrement = amountIncrement;
    }

    public Set<Rules> getRules() {
        return rules;
    }

    public void setRules(Set<Rules> rules) {
        this.rules = rules;
    }

    public BaseEntity getBaseEntity() {
        return baseEntity;
    }

    public void setBaseEntity(BaseEntity baseEntity) {
        this.baseEntity = baseEntity;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getMaxPointsEarn() {
        return maxPointsEarn;
    }

    public void setMaxPointsEarn(Integer maxPointsEarn) {
        this.maxPointsEarn = maxPointsEarn;
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
}

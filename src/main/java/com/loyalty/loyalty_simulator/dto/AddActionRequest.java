package com.loyalty.loyalty_simulator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class AddActionRequest {
    private Boolean addition;
    private Boolean deduction;
    private Integer point;
    @JsonProperty("amount_increment")
    private Integer amountIncrement;
    @JsonProperty("start_date")
    private LocalDateTime startDate;
    @JsonProperty("end_date")
    private LocalDateTime endDate;
    private Integer priority;
    @JsonProperty("max_points_earn")
    private Integer maxPointsEarn;

    public Integer getAmountIncrement() {
        return amountIncrement;
    }

    public void setAmountIncrement(Integer amountIncrement) {
        this.amountIncrement = amountIncrement;
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
}

package com.loyalty.loyalty_simulator.dto;

public class AddActionRequest {
    private Boolean addition;
    private Boolean deduction;
    private Integer point;

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
}
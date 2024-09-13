package com.loyalty.loyalty_simulator.dto;

public class ComparisonOperator {
    private Boolean greaterThan;
    private Boolean greaterThanEqual;
    private Boolean lesserThan;
    private Boolean lesserThanEqual;
    private Boolean equal;
    private Boolean isFullfilled;

    public Boolean getFullfilled() {
        return isFullfilled;
    }

    public void setFullfilled(Boolean fullfilled) {
        isFullfilled = fullfilled;
    }

    public Boolean getGreaterThanEqual() {
        return greaterThanEqual;
    }

    public void setGreaterThanEqual(Boolean greaterThanEqual) {
        this.greaterThanEqual = greaterThanEqual;
    }

    public Boolean getLesserThanEqual() {
        return lesserThanEqual;
    }

    public void setLesserThanEqual(Boolean lesserThanEqual) {
        this.lesserThanEqual = lesserThanEqual;
    }

    public Boolean getGreaterThan() {
        return greaterThan;
    }

    public void setGreaterThan(Boolean greaterThan) {
        this.greaterThan = greaterThan;
    }

    public Boolean getLesserThan() {
        return lesserThan;
    }

    public void setLesserThan(Boolean lesserThan) {
        this.lesserThan = lesserThan;
    }

    public Boolean getEqual() {
        return equal;
    }

    public void setEqual(Boolean equal) {
        this.equal = equal;
    }
}

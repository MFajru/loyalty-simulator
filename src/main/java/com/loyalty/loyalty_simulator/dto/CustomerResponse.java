package com.loyalty.loyalty_simulator.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.loyalty.loyalty_simulator.models.BaseEntity;
import jakarta.persistence.Embedded;

public class CustomerResponse {
    private String cif;
    private String name;
    private Integer point;
    @Embedded
    @JsonUnwrapped
    private BaseEntity baseEntity;

    public CustomerResponse(String cif, String name, Integer point, BaseEntity baseEntity) {
        this.cif = cif;
        this.name = name;
        this.point = point;
        this.baseEntity = baseEntity;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public BaseEntity getBaseEntity() {
        return baseEntity;
    }

    public void setBaseEntity(BaseEntity baseEntity) {
        this.baseEntity = baseEntity;
    }
}

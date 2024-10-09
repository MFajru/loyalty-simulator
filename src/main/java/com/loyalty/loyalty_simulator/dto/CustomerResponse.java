package com.loyalty.loyalty_simulator.dto;

public class CustomerResponse {
    private String cif;
    private String name;
    private Integer point;

    public CustomerResponse(String cif, String name, Integer point) {
        this.cif = cif;
        this.name = name;
        this.point = point;
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
}

package com.loyalty.loyalty_simulator.dto;

public class AddActionToCustomerReq {
    private String cif;
    private Long rulesActionId;

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public Long getRulesActionId() {
        return rulesActionId;
    }

    public void setRulesActionId(Long rulesActionId) {
        this.rulesActionId = rulesActionId;
    }
}

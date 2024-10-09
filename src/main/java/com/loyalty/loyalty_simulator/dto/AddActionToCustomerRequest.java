package com.loyalty.loyalty_simulator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddActionToCustomerRequest {
    private String cif;
    @JsonProperty("rules_action_id")
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

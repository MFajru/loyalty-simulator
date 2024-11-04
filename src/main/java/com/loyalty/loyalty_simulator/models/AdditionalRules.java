package com.loyalty.loyalty_simulator.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.persistence.*;

@Entity
@Table(name = "zzz_additional_rules")
public class AdditionalRules {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonProperty("acc_merchant_id")
    private String accMerchantId;
    @JsonProperty("acc_terminal_id")
    private String accTerminalId;

    @JsonUnwrapped
    @JsonIgnore
    @Embedded
    private BaseEntity baseEntity;

    public AdditionalRules(String accMerchantId, String accTerminalId) {
        this.accMerchantId = accMerchantId;
        this.accTerminalId = accTerminalId;
    }

    public AdditionalRules() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccMerchantId() {
        return accMerchantId;
    }

    public void setAccMerchantId(String accMerchantId) {
        this.accMerchantId = accMerchantId;
    }

    public String getAccTerminalId() {
        return accTerminalId;
    }

    public void setAccTerminalId(String accTerminalId) {
        this.accTerminalId = accTerminalId;
    }

    public BaseEntity getBaseEntity() {
        return baseEntity;
    }

    public void setBaseEntity(BaseEntity baseEntity) {
        this.baseEntity = baseEntity;
    }
}

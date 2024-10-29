package com.loyalty.loyalty_simulator.models;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rule_id", referencedColumnName = "id")
    private Rules rule;

    @Embedded
    private BaseEntity baseEntity;

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

    public Rules getRule() {
        return rule;
    }

    public void setRule(Rules rule) {
        this.rule = rule;
    }

    public BaseEntity getBaseEntity() {
        return baseEntity;
    }

    public void setBaseEntity(BaseEntity baseEntity) {
        this.baseEntity = baseEntity;
    }
}

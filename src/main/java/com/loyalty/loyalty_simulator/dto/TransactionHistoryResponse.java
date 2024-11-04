package com.loyalty.loyalty_simulator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.loyalty.loyalty_simulator.models.BaseEntity;
import jakarta.persistence.Embedded;

import java.util.Date;

@JsonPropertyOrder({
        "tran_code",
        "tran_date",
        "amount",
        "base_entity"
})
public class TransactionHistoryResponse {
    @JsonProperty("tran_code")
    private String tranCode;

    private Integer amount;

    @JsonProperty("tran_date")
    private Date tranDate;

    @Embedded
    @JsonUnwrapped
    private BaseEntity baseEntity;

    public TransactionHistoryResponse(String tranCode, Integer amount, Date tranDate, BaseEntity baseEntity) {
        this.tranCode = tranCode;
        this.amount = amount;
        this.tranDate = tranDate;
        this.baseEntity = baseEntity;
    }

    public String getTranCode() {
        return tranCode;
    }

    public void setTranCode(String tranCode) {
        this.tranCode = tranCode;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getTranDate() {
        return tranDate;
    }

    public void setTranDate(Date tranDate) {
        this.tranDate = tranDate;
    }

    public BaseEntity getBaseEntity() {
        return baseEntity;
    }

    public void setBaseEntity(BaseEntity baseEntity) {
        this.baseEntity = baseEntity;
    }
}

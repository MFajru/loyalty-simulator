package com.loyalty.loyalty_simulator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class TransactionHistoryResponse {
    @JsonProperty("tran_code")
    private String tranCode;

    private Integer amount;

    @JsonProperty("tran_date")
    private Date tranDate;

    public TransactionHistoryResponse(String tranCode, Integer amount, Date tranDate) {
        this.tranCode = tranCode;
        this.amount = amount;
        this.tranDate = tranDate;
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
}

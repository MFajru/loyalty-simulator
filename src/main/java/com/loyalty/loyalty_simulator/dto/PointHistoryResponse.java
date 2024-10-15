package com.loyalty.loyalty_simulator.dto;

import com.loyalty.loyalty_simulator.models.PointHistory;

import java.util.ArrayList;
import java.util.List;

public class PointHistoryResponse {
    private Long id;
    private Integer amount;
    private String cif;
    private Long actionId;
    private String tranCode;

    public PointHistoryResponse(Long id, Integer amount, String cif, Long actionId, String tranCode) {
        this.id = id;
        this.amount = amount;
        this.cif = cif;
        this.actionId = actionId;
        this.tranCode = tranCode;
    }

    public PointHistoryResponse() {}

    public List<PointHistoryResponse> mappingPointHistories(List<PointHistory> pointHistories) {
        List<PointHistoryResponse> res = new ArrayList<>();
        for (PointHistory pointHistory: pointHistories) {
            PointHistoryResponse temp = new PointHistoryResponse(pointHistory.getId(), pointHistory.getAmount(), pointHistory.getCustomers().getCif(), pointHistory.getRulesAction().getId(),pointHistory.getTransactions().getTranCode());
            res.add(temp);
        }
        return res;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public Long getActionId() {
        return actionId;
    }

    public void setActionId(Long actionId) {
        this.actionId = actionId;
    }

    public String getTranCode() {
        return tranCode;
    }

    public void setTranCode(String tranCode) {
        this.tranCode = tranCode;
    }
}

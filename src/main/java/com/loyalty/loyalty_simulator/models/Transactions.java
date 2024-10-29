package com.loyalty.loyalty_simulator.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "zzz_transactions")
public class Transactions {
    @Id
    @JsonProperty("tran_code")
    private String tranCode;
    private String tran_type;
    private Integer amount;
    @JsonProperty("tran_date")
    private Date tranDate;
    private Boolean isCardBased;
    @JsonProperty("merchant_id")
    private String merchantId;
    @JsonProperty("terminal_id")
    private String terminalId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cust_cif" , referencedColumnName = "cif")
    @JsonBackReference
    private Customers customers;

    @JsonProperty("tran_point_history")
    @OneToMany(mappedBy = "transactions")
    @JsonManagedReference("pointHistoryRef")
    private List<PointHistory> tranPointHistories;

    @Embedded
    private BaseEntity baseEntity;

    public List<PointHistory> getTranPointHistories() {
        return tranPointHistories;
    }

    public void setTranPointHistories(List<PointHistory> tranPointHistor) {
        this.tranPointHistories = tranPointHistor;
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

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public String getTran_type() {
        return tran_type;
    }

    public void setTran_type(String tran_type) {
        this.tran_type = tran_type;
    }

    public Boolean getCardBased() {
        return isCardBased;
    }

    public void setCardBased(Boolean cardBased) {
        isCardBased = cardBased;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public BaseEntity getBaseEntity() {
        return baseEntity;
    }

    public void setBaseEntity(BaseEntity baseEntity) {
        this.baseEntity = baseEntity;
    }
}

package com.loyalty.loyalty_simulator.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "zzz_transactions")
public class Transactions {
    @Id
    @JsonProperty("tran_code")
    private String tranCode;

    private Integer amount;

    @JsonProperty("tran_date")
    private Date tranDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cust_cif" , referencedColumnName = "cif")
    @JsonBackReference
    private Customers customers;

    @JsonProperty("tran_point_history")
    @OneToOne(mappedBy = "transactions")
    @JsonBackReference("pointHistoryRef")
    private PointHistory tranPointHistory;

    public PointHistory getTranPointHistory() {
        return tranPointHistory;
    }

    public void setTranPointHistory(PointHistory pointHistory) {
        this.tranPointHistory = pointHistory;
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



}

package com.loyalty.loyalty_simulator.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "zzz_point_history")
public class PointHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "tran_id", referencedColumnName = "tranCode")
    @JsonBackReference("pointHistoryRef")
    private Transactions transactions;

    @ManyToOne
    @JoinColumn(name = "cif", referencedColumnName = "cif")
    @JsonBackReference("ptHisCustomer")
    private Customers customers;

    @ManyToOne
    @JoinColumn(name = "action_id", referencedColumnName = "id")
    @JsonProperty("rules_action")
    @JsonIgnore
    private RulesAction rulesAction;

    @Embedded
    private BaseEntity baseEntity;

    public RulesAction getRulesAction() {
        return rulesAction;
    }

    public void setRulesAction(RulesAction rulesAction) {
        this.rulesAction = rulesAction;
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

    public Transactions getTransactions() {
        return transactions;
    }

    public void setTransactions(Transactions transactions) {
        this.transactions = transactions;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public BaseEntity getBaseEntity() {
        return baseEntity;
    }

    public void setBaseEntity(BaseEntity baseEntity) {
        this.baseEntity = baseEntity;
    }
}

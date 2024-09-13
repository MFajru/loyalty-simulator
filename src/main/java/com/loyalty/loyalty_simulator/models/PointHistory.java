package com.loyalty.loyalty_simulator.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "zzz_point_history")
public class PointHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer amount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tran_id", referencedColumnName = "tranCode")
    private Transactions transactions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cif", referencedColumnName = "cif")
    @JsonBackReference
    private Customers customers;

    @OneToMany(mappedBy = "pointHistory")
    private Set<PointHistoryRules> pointHistoryRules = new HashSet<>();

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

    public Set<PointHistoryRules> getPointHistoryRules() {
        return pointHistoryRules;
    }

    public void setPointHistoryRules(Set<PointHistoryRules> pointHistoryRules) {
        this.pointHistoryRules = pointHistoryRules;
    }
}

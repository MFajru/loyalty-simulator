package com.loyalty.loyalty_simulator.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "zzz_customers")
public class Customers {
    @Id
    private String cif;
    private String name;
    private Integer point;

    @JsonManagedReference
    @OneToMany(mappedBy = "customers")
    private Set<Transactions> transactions = new HashSet<>();

    @JsonProperty("point_histories")
    @JsonManagedReference("ptHisCustomer")
    @OneToMany(mappedBy = "customers")
    private Set<PointHistory> pointHistories = new HashSet<>();

    @JsonProperty("rules_matrix")
    @JsonManagedReference("actionCust")
    @OneToMany(mappedBy = "customer")
     private Set<CustomerAction> customerActions = new HashSet<>();

    public Set<PointHistory> getPointHistories() {
        return pointHistories;
    }

    public void setPointHistories(Set<PointHistory> pointHistories) {
        this.pointHistories = pointHistories;
    }

    public Set<Transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transactions> transactions) {
        this.transactions = transactions;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Set<CustomerAction> getCustomerActions() {
        return customerActions;
    }

    public void setCustomerActions(Set<CustomerAction> customerActions) {
        this.customerActions = customerActions;
    }
}

package com.loyalty.loyalty_simulator.models;

import jakarta.persistence.*;

@Entity
@Table(name = "zzz_customer_action")
public class CustomerAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Customers customer;

    @ManyToOne
    private RulesAction action;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public RulesAction getAction() {
        return action;
    }

    public void setAction(RulesAction action) {
        this.action = action;
    }
}

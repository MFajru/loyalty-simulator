package com.loyalty.loyalty_simulator.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "zzz_customer_action")
public class CustomerAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonBackReference("actionCust")
    @JoinColumn(name = "cif", referencedColumnName = "cif")
    @ManyToOne(fetch = FetchType.LAZY)
    private Customers customer;

    @JoinColumn(name = "action_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private RulesAction action;

    @Embedded
    private BaseEntity baseEntity;

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

    public BaseEntity getBaseEntity() {
        return baseEntity;
    }

    public void setBaseEntity(BaseEntity baseEntity) {
        this.baseEntity = baseEntity;
    }
}

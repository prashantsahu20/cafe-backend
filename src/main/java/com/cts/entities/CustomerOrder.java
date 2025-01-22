package com.cts.entities;

import lombok.Data;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Data
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerOrderId;

    @ManyToOne
    @JoinColumn(name = "customerId", nullable = false)
    private Customer customer;

    @Temporal(TemporalType.DATE)
    private Date orderDate;

    private double totalAmount;

    @ManyToMany
    @JoinTable(
        name = "CustomerOrder_Items",
        joinColumns = @JoinColumn(name = "customerOrderId"),
        inverseJoinColumns = @JoinColumn(name = "itemId")
    )
    private List<Items> items;
}

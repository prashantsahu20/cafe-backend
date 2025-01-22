package com.cts.entities;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    @ManyToOne
    @JoinColumn(name = "customerOrderId", nullable = false)
    private CustomerOrder customerOrder;

    @ManyToOne
    @JoinColumn(name = "itemId", nullable = false)
    private Items item;

    private int quantity;
    private double itemPrice;
}

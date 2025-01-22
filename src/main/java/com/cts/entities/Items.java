package com.cts.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Items {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private double price;
}

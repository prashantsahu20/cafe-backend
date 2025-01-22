package com.cts.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    
    
    @NotEmpty(message = "Phone is required")
    @Size(min = 10, message = "Phone should have at least 10 characters")
    @Pattern(regexp = "\\d+", message = "Phone should only contain digits")
    private String phone;
    
    private String address;
    
    @NotEmpty(message = "Password is required")
    @Size(min = 5, message = "Password should have at least 5 characters")
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+=<>?{}~|]).{5,}$",
        message = "Password must include at least one lowercase letter, one uppercase letter, one number, and one special character"
    )
    private String password;
    
    private double salary;
    private String role;
}

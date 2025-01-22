package com.cts.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cts.entities.Customer;
import com.cts.service.CustomerService;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");
        Optional<Customer> customerOptional = customerService.verifyPassword(email, password);
        if (customerOptional.isPresent()) {
            return ResponseEntity.ok(customerOptional.get());
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }
}

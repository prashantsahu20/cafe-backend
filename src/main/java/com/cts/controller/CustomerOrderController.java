package com.cts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cts.entities.CustomerOrder;
import com.cts.service.CustomerOrderService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/customerOrders")
public class CustomerOrderController {

    @Autowired
    private CustomerOrderService customerOrderService;

    @PostMapping
    public CustomerOrder createCustomerOrder(@Valid @RequestBody CustomerOrder customerOrder) {
        return customerOrderService.saveCustomerOrder(customerOrder);
    }

    @GetMapping
    public List<CustomerOrder> getAllCustomerOrders() {
        return customerOrderService.getAllCustomerOrders();
    }

    @GetMapping("/{id}")
    public CustomerOrder getCustomerOrderById(@PathVariable Long id) {
        return customerOrderService.getCustomerOrderById(id);
    }

    @PutMapping("/{id}")
    public CustomerOrder updateCustomerOrder(@PathVariable Long id, @Valid @RequestBody CustomerOrder updatedCustomerOrder) {
        return customerOrderService.updateCustomerOrder(id, updatedCustomerOrder);
    }

    @DeleteMapping("/{id}")
    public String deleteCustomerOrder(@PathVariable Long id) {
        customerOrderService.deleteCustomerOrder(id);
        return "CustomerOrder deleted successfully";
    }
}

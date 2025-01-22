package com.cts.service;

import com.cts.entities.CustomerOrder;
import java.util.List;

public interface CustomerOrderService {
    CustomerOrder saveCustomerOrder(CustomerOrder customerOrder);
    List<CustomerOrder> getAllCustomerOrders();
    CustomerOrder getCustomerOrderById(Long id);
    CustomerOrder updateCustomerOrder(Long id, CustomerOrder updatedCustomerOrder);
    void deleteCustomerOrder(Long id);
    void calculateTotalAmount(CustomerOrder customerOrder);
//    void validateAndSetItems(CustomerOrder customerOrder);
}

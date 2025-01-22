package com.cts.serviceimpl;

import com.cts.entities.CustomerOrder;
import com.cts.entities.Items;
import com.cts.exception.ResourceNotFoundException;
import com.cts.repository.CustomerOrderRepository;
import com.cts.repository.ItemsRepository;
import com.cts.service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Autowired
    private ItemsRepository itemsRepository;

    @Override
    public CustomerOrder saveCustomerOrder(CustomerOrder customerOrder) {
        validateAndSetItems(customerOrder);
        calculateTotalAmount(customerOrder);
        return customerOrderRepository.save(customerOrder);
    }

    @Override
    public List<CustomerOrder> getAllCustomerOrders() {
        return customerOrderRepository.findAll();
    }

    @Override
    public CustomerOrder getCustomerOrderById(Long id) {
        return customerOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CustomerOrder not found with id: " + id));
    }

    @Override
    public CustomerOrder updateCustomerOrder(Long id, CustomerOrder updatedCustomerOrder) {
        return customerOrderRepository.findById(id).map(customerOrder -> {
            customerOrder.setOrderDate(updatedCustomerOrder.getOrderDate());
            customerOrder.setCustomer(updatedCustomerOrder.getCustomer());
            customerOrder.setItems(updatedCustomerOrder.getItems());
            validateAndSetItems(customerOrder);
            calculateTotalAmount(customerOrder);
            return customerOrderRepository.save(customerOrder);
        }).orElseThrow(() -> new ResourceNotFoundException("CustomerOrder not found with id: " + id));
    }

    @Override
    public void deleteCustomerOrder(Long id) {
        if (!customerOrderRepository.existsById(id)) {
            throw new ResourceNotFoundException("CustomerOrder not found with id: " + id);
        }
        customerOrderRepository.deleteById(id);
    }

    @Override
    public void calculateTotalAmount(CustomerOrder customerOrder) {
        double totalAmount = 0.0;
        for (Items item : customerOrder.getItems()) {
            Items fetchedItem = itemsRepository.findById(item.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + item.getId()));
            totalAmount += fetchedItem.getPrice();
        }
        customerOrder.setTotalAmount(totalAmount);
    }

    public void validateAndSetItems(CustomerOrder customerOrder) {
        List<Items> items = customerOrder.getItems();
        if (items == null || items.isEmpty()) {
            throw new ResourceNotFoundException("Items not provided in the order");
        }
        for (Items item : items) {
            Items fetchedItem = itemsRepository.findById(item.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + item.getId()));
            item.setName(fetchedItem.getName());
            item.setPrice(fetchedItem.getPrice());
        }
    }
}

package com.cts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.cts.entities.Items;
import com.cts.service.ItemsService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = "https://yours-cafe-dine.netlify.app")
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Items createItem(@Valid @RequestBody Items item) {
        return itemsService.saveItem(item);
    }

    @PostMapping("/bulk")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Items> createAllItems(@Valid @RequestBody List<Items> items) {
        return itemsService.saveAllItems(items);
    }

    @GetMapping
    public List<Items> getAllItems() {
        return itemsService.getAllItems();
    }

    @GetMapping("/{id}")
    public Items getItemById(@PathVariable Long id) {
        return itemsService.getItemById(id);
    }

    @PutMapping("/{id}")
    public Items updateItem(@PathVariable Long id, @Valid @RequestBody Items updatedItem) {
        return itemsService.updateItem(id, updatedItem);
    }

    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable Long id) {
        itemsService.deleteItem(id);
        return "Item deleted successfully";
    }
}

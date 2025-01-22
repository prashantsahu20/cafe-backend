package com.cts.service;

import java.util.List;

import com.cts.entities.Items;

public interface ItemsService {
    Items saveItem(Items item);
    List<Items> saveAllItems(List<Items> items);
    List<Items> getAllItems();
    Items getItemById(Long id);
    Items updateItem(Long id, Items updatedItem);
    void deleteItem(Long id);
}

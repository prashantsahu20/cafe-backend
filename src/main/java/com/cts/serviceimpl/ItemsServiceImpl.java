package com.cts.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.entities.Items;
import com.cts.exception.ResourceNotFoundException;
import com.cts.repository.ItemsRepository;
import com.cts.service.ItemsService;

import java.util.List;

@Service
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    private ItemsRepository itemsRepository;

    @Override
    public Items saveItem(Items item) {
        return itemsRepository.save(item);
    }

    @Override
    public List<Items> saveAllItems(List<Items> items) {
        return itemsRepository.saveAll(items);
    }

    @Override
    public List<Items> getAllItems() {
        return itemsRepository.findAll();
    }

    @Override
    public Items getItemById(Long id) {
        return itemsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + id));
    }

    @Override
    public Items updateItem(Long id, Items updatedItem) {
        return itemsRepository.findById(id).map(item -> {
            item.setName(updatedItem.getName());
            item.setPrice(updatedItem.getPrice());
            return itemsRepository.save(item);
        }).orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + id));
    }

    @Override
    public void deleteItem(Long id) {
        if (!itemsRepository.existsById(id)) {
            throw new ResourceNotFoundException("Item not found with id: " + id);
        }
        itemsRepository.deleteById(id);
    }
}

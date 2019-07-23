package com.mo.guard.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/items")
public class TestController {

    Map<String, Item> itemRepo = new HashMap();

    @Data
    public static class Item {
        String itemId = "";
        String itemName = "";
        int itemCount = 0;
    }

    @RequestMapping(method= RequestMethod.GET)
    public Collection<Item> getItems() {
        return itemRepo.values();
    }

    @RequestMapping(method= RequestMethod.POST)
    public Item createItem(@RequestBody Item newItem) {
        String newItemId = UUID.randomUUID() + "";
        newItem.setItemId(newItemId);
        itemRepo.put(newItemId, newItem);
        return newItem;
    }

    @RequestMapping(value = "/{itemId}", method= RequestMethod.GET)
    public Item getItem(@PathVariable String itemId) {
        return itemRepo.get(itemId);
    }

    @RequestMapping(value = "/{itemId}", method= RequestMethod.PUT)
    public Item updateItem(@PathVariable String itemId, @RequestBody Item updatedItem) {
        Item selectedItem = itemRepo.get(itemId);
        selectedItem.setItemId(updatedItem.getItemId().equals("") ? itemId : updatedItem.getItemId());
        selectedItem.setItemName(updatedItem.getItemName());
        selectedItem.setItemCount(updatedItem.getItemCount());
        return selectedItem;
    }

    @RequestMapping(value = "/{itemId}", method= RequestMethod.DELETE)
    public Item deleteItem(@PathVariable String itemId) {
        Item deletedItem = itemRepo.remove(itemId);
        return deletedItem;
    }
}

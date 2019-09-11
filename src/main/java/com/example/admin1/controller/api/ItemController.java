package com.example.admin1.controller.api;

import com.example.admin1.interfaces.CrudInterface;
import com.example.admin1.model.network.Header;
import com.example.admin1.model.network.request.ItemRequest;
import com.example.admin1.model.network.response.ItemResponse;
import com.example.admin1.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/item")
public class ItemController implements CrudInterface<ItemRequest, ItemResponse> {

    @Autowired
    private ItemService itemService;

    @Override
    @PostMapping("")
    public Header<ItemResponse> create(@RequestBody Header<ItemRequest> request) {
        return itemService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<ItemResponse> read(@PathVariable Long id) {
        return null;
    }

    @Override
    @PutMapping("")
    public Header<ItemResponse> update(@RequestBody Header<ItemRequest> request) {
        return null;
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        return null;
    }
}

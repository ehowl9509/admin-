package com.example.admin1.controller.api;

import com.example.admin1.interfaces.CrudInterface;
import com.example.admin1.model.network.Header;
import com.example.admin1.model.network.request.CategoryRequest;
import com.example.admin1.model.network.response.CategoryResponse;
import com.example.admin1.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController implements CrudInterface<CategoryRequest, CategoryResponse> {
    @Autowired
    private CategoryService categoryService;

    @Override
    @PostMapping("")
    public Header<CategoryResponse> create(@RequestBody Header<CategoryRequest> request) {
        return categoryService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<CategoryResponse> read(@PathVariable Long id) {
        return categoryService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<CategoryResponse> update(@RequestBody Header<CategoryRequest> request) {
        return categoryService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        return categoryService.delete(id);
    }
}

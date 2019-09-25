package com.example.admin1.service;

import com.example.admin1.interfaces.CrudInterface;
import com.example.admin1.model.Category;
import com.example.admin1.model.network.Header;
import com.example.admin1.model.network.request.CategoryRequest;
import com.example.admin1.model.network.response.CategoryResponse;
import com.example.admin1.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CategoryService implements CrudInterface<CategoryRequest, CategoryResponse> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Header<CategoryResponse> create(Header<CategoryRequest> request) {
        CategoryRequest categoryRequest = request.getData();
        Category category = Category.builder()
                .title(categoryRequest.getTitle())
                .type(categoryRequest.getType())
                .build();
        Category newCategory = categoryRepository.save(category);
        return response(newCategory);
    }

    @Override
    public Header<CategoryResponse> read(Long id) {
        return categoryRepository.findById(id)
                .map(category -> response(category))
                .orElse(Header.ERROR("데이터없음"));
    }

    @Override
    public Header<CategoryResponse> update(Header<CategoryRequest> request) {
        CategoryRequest categoryRequest = request.getData();
        Optional<Category> optional = categoryRepository.findById(categoryRequest.getId());
        return optional.map(category -> {
            category.setTitle(categoryRequest.getTitle())
                    .setType(categoryRequest.getType());
            return category;
        })
                .map(category -> categoryRepository.save(category))
                .map(category -> response(category))
                .orElse(Header.ERROR("데이터없음"));
    }

    @Override
    public Header delete(Long id) {
        return categoryRepository.findById(id)
                .map(category -> {
                    categoryRepository.delete(category);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터없음"));
    }


    private Header<CategoryResponse> response(Category category){
        CategoryResponse categoryResponse = CategoryResponse.builder()
                .id(category.getId())
                .title(category.getTitle())
                .type(category.getType())
                .build();
        return Header.OK(categoryResponse);
    }
}

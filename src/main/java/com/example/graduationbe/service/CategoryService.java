package com.example.graduationbe.service;

import com.example.graduationbe.dto.CategoryDto;
import com.example.graduationbe.entities.commerce.Category;

import java.util.List;

public interface CategoryService {

    Category createCategory(Category category);

    List<CategoryDto> getAll();

    CategoryDto getCategoryById(Long cateId);

    Category updateCategory(Category category);

    void deleteById(Long cateId);
}

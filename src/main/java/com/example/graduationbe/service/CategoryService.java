package com.example.graduationbe.service;

import com.example.graduationbe.dto.CategoryDto;
import com.example.graduationbe.entities.commerce.Category;

import java.util.List;

public interface CategoryService {

    Category createCategory(Category category) throws Exception;

    List<CategoryDto> getAll();

    CategoryDto getCategoryById(Long cateId);

    Category updateCategory(Category category) throws Exception;

    void deleteById(Long cateId);
}

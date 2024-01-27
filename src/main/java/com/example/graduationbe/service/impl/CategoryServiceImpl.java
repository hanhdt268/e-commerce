package com.example.graduationbe.service.impl;

import com.example.graduationbe.dto.CategoryDto;
import com.example.graduationbe.entities.commerce.Category;
import com.example.graduationbe.repository.CategoryRepository;
import com.example.graduationbe.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
//    @CacheEvict(value = {"category"}, allEntries = true)
    public Category createCategory(Category category) throws Exception {
        Category category1 = this.categoryRepository.findByTitle(category.getTitle());
        if (category1 != null) {
            System.out.println("Category is already there!!!");
            throw new Exception();
        } else {
            category1 = this.categoryRepository.save(category);
        }
        return category1;
    }

    @Override
//    @Cacheable("category")
    public List<CategoryDto> getAll() {
        List<Category> categories = this.categoryRepository.findAll();
        List<CategoryDto> categoryDtos = categories.stream().map((element) ->
                this.modelMapper.map(element, CategoryDto.class)).collect(Collectors.toList());
        return categoryDtos;
    }

    @Override
//    @Cacheable("category")
    public CategoryDto getCategoryById(Long cateId) {
        Category category = this.categoryRepository.findById(cateId).orElse(null);
        return this.modelMapper.map(category, CategoryDto.class);
    }

    @Override
//    @CacheEvict(value = {"category", "product"}, allEntries = true)
    public Category updateCategory(Category category) throws Exception {
        Category category1 = this.categoryRepository.findByTitle(category.getTitle());
        if (category1 != null) {
            System.out.println("Category is already there!!!");
            throw new Exception();
        } else {
            category1 = this.categoryRepository.save(category);
        }
        return category1;
    }

    @Override
//    @CacheEvict(value = {"category", "manufacture", "product"}, allEntries = true)
    public void deleteById(Long cateId) {
        this.categoryRepository.deleteById(cateId);
    }
}

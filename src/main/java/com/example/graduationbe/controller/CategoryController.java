package com.example.graduationbe.controller;


import com.example.graduationbe.dto.CategoryDto;
import com.example.graduationbe.entities.ResponseObject;
import com.example.graduationbe.entities.commerce.Category;
import com.example.graduationbe.service.impl.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/category")
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    @PostMapping("/")
    public ResponseEntity<ResponseObject> addCategory(@RequestBody Category category) throws Exception {
        return ResponseEntity.ok(ResponseObject.builder()
                .message("add category successfully").http(HttpStatus.OK).ob(category).build());
    }

    //update
    @PutMapping("/")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) throws Exception {
        return ResponseEntity.ok(this.categoryService.updateCategory(category));
    }

    //get category by id
    @GetMapping("/{cId}")
    public ResponseEntity<ResponseObject> getCategoriesById(@PathVariable("cId") Long cId) {
        return ResponseEntity.ok(ResponseObject.builder()
                .message("get information category successfully").http(HttpStatus.OK).ob(categoryService.getCategoryById(cId)).build());
    }

    //get all
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getCategories() {
        return ResponseEntity.ok(this.categoryService.getAll());
    }

    @DeleteMapping("/{cId}")
    public void delete(@PathVariable("cId") Long cId) {
        this.categoryService.deleteById(cId);
    }
}

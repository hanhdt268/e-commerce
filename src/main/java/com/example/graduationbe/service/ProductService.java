package com.example.graduationbe.service;

import com.example.graduationbe.dto.ProductDto;
import com.example.graduationbe.entities.commerce.Category;
import com.example.graduationbe.entities.commerce.Manufacturer;
import com.example.graduationbe.entities.commerce.Product;

import java.util.List;

public interface ProductService {
    Product creteaProduct(Product product);

    Product updateProduct(Product product);

    ProductDto getProductById(Long pId);

    List<ProductDto> getProducts();

    void deleteProduct(Long pId);

    List<ProductDto> getProductOfCategory(Category category, int pageNumber, String searchKey);

    List<ProductDto> getActiveProduct(String searchKey);


    List<ProductDto> getProductOfManufacturer(Manufacturer manufacturer, int pageNumber, String searchKey);

//    List<ProductDto> getProductDetails(boolean isSingleProductCheckout, Long pid);

}

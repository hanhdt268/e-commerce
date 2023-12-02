package com.example.graduationbe.service.impl;

import com.example.graduationbe.dto.CartDto;
import com.example.graduationbe.dto.ProductDto;
import com.example.graduationbe.entities.commerce.Cart;
import com.example.graduationbe.entities.commerce.Category;
import com.example.graduationbe.entities.commerce.Manufacturer;
import com.example.graduationbe.entities.commerce.Product;
import com.example.graduationbe.repository.CartRepository;
import com.example.graduationbe.repository.ProductRepository;
import com.example.graduationbe.repository.UserRepository;
import com.example.graduationbe.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    private final CartRepository cartRepository;

    @Override
//    @CacheEvict(value = {"product", "Desc", "Asc", "Selling"}, allEntries = true)
    public Product creteaProduct(Product product) {
        return this.productRepository.save(product);
    }

    @Override
//    @CachePut(value = {"product", "Desc", "Asc", "Selling"})
    public Product updateProduct(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public ProductDto getProductById(Long pid) {
        Product products = this.productRepository.findById(pid).get();
        return this.modelMapper.map(products, ProductDto.class);
    }

    @Override
//    @Cacheable("product")
    public List<ProductDto> getProducts() {
        List<Product> products = this.productRepository.findAll();
        return products.stream().map(product -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteProduct(Long pId) {
        this.productRepository.deleteById(pId);
    }

    @Override
//    @Cacheable(value = "product", key = "#category.cateId")
    public List<ProductDto> getProductOfCategory(Category category, int pageNumber, String searchKey) {
        Pageable pageable = PageRequest.of(pageNumber, 8);

//        return this.productRepository.findByCategory(category, pageable);
        if (searchKey.equals("")) {
            List<Product> products = this.productRepository.findByCategory(category);
            List<ProductDto> productDtos = products.stream().map(product -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
            return productDtos;
        } else {
            return null;
        }
    }

    public List<ProductDto> getProductBySuggest(Category category, double minPrice, double maxPrice, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 4);
        List<Product> products = this.productRepository.findByCate(category, minPrice, maxPrice, pageable);
        List<ProductDto> productDtos = products.stream().map(product -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
        return productDtos;
    }


    @Override
//    @Cacheable(value = "product")
    public List<ProductDto> getActiveProduct(int pageNumber, String searchKey) {
        Pageable pageable = PageRequest.of(pageNumber, 8);
        if (searchKey.equals("")) {
//            return (List<Product>) this.productRepository.findAll(pageable);
            List<Product> products = this.productRepository.findByActive(true);
            List<ProductDto> productDtos = products.stream().map(product -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
            return productDtos;
        } else {
            List<Product> products = this.productRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                    searchKey, searchKey, pageable
            );

            List<ProductDto> productDtos = products.stream().map(product -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
            return productDtos;
        }
    }

    //    @Cacheable("product")
    public List<ProductDto> getProducts(String searchKey) {
        Pageable pageable = PageRequest.of(0, 8);
        if (searchKey.equals("")) {
//            return (List<Product>) this.productRepository.findAll(pageable);
            List<Product> products = this.productRepository.findByActive(true);
            List<ProductDto> productDtos = products.stream().map(product -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
            return productDtos;

        } else {

            List<Product> products = this.productRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                    searchKey, searchKey, pageable
            );

            List<ProductDto> productDtos = products.stream().map(product -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
            return productDtos;
        }
    }

    public List<ProductDto> getProductByPriceAsc(String searchKey, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 8);
        if (searchKey.equals("")) {
//            return (List<Product>) this.productRepository.findAll(pageable);
            List<Product> products = this.productRepository.findAll();
            List<ProductDto> productDtos = products.stream().map(product -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
            return productDtos;

        } else {

            List<Product> products = this.productRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrderByDiscountPriceAsc(
                    searchKey, searchKey, pageable
            );

            List<ProductDto> productDtos = products.stream().map(product -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
            return productDtos;
        }
    }

    public List<ProductDto> getProductByBestSelling(String searchKey, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 8);
        if (searchKey.equals("")) {
//            return (List<Product>) this.productRepository.findAll(pageable);
            List<Product> products = this.productRepository.findAll();
            List<ProductDto> productDtos = products.stream().map(product -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
            return productDtos;

        } else {

            List<Product> products = this.productRepository.findByTitleContainingOrDescriptionContainingCaseInsensitive(
                    searchKey, pageable
            );
            List<ProductDto> productDtos = products.stream().map(product -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
            return productDtos;
        }
    }

    public List<ProductDto> testApi(String searchKey, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 8);
        if (searchKey.equals("")) {
//            return (List<Product>) this.productRepository.findAll(pageable);
            List<Product> products = this.productRepository.findAll();
            List<ProductDto> productDtos = products.stream().map(product -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
            return productDtos;

        } else {
            List<Product> products = this.productRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrderByDiscountPriceDesc(
                    searchKey, searchKey, pageable
            );
            List<ProductDto> productDtos = products.stream().map(product -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
            return productDtos;
        }
    }

    @Override
//    @Cacheable(value = "product", key = "#manufacturer.manuId")
    public List<ProductDto> getProductOfManufacturer(Manufacturer manufacturer, int pageNumber, String searchKey) {
        Pageable pageable = PageRequest.of(pageNumber, 8);
        if (searchKey.equals("")) {
            List<Product> products = this.productRepository.findByManufacturerAndActive(manufacturer, true);
            List<ProductDto> productDtos = products.stream().map(product -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
            return productDtos;
        } else {
            List<Product> products = this.productRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                    searchKey, searchKey, pageable
            );
            List<ProductDto> productDtos = products.stream().map(product -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
            return productDtos;
        }
    }
//
//    @Override
//    public List<ProductDto> getProductDetails(boolean isSingleProductCheckout, Long pid) {
//        if (isSingleProductCheckout && pid != null) {
//            List<ProductDto> list = new ArrayList<>();
//            Product product = this.productRepository.findById(pid).get();
//            ProductDto productDto = this.modelMapper.map(product, ProductDto.class);
//            list.add(productDto);
//            return list;
//        } else {
//            String username = JwtAuthenticationFilter.USER_CURRENT;
//            User user = this.userRepository.findByUsername(username);
//            List<Cart> cart = this.cartRepository.findByUser(user);
//            List<CartDto> cartDtos = cart.stream().map((element) -> modelMapper.map(element, CartDto.class)).collect(Collectors.toList());
//            cartDtos.stream().map(CartDto::getQuantity);
//            return cartDtos.stream().map(CartDto::getProduct).collect(Collectors.toList());
//        }
//    }

    public List<CartDto> getProductForPayment(Long userId, List<Long> longs) {
        List<Cart> cart = this.cartRepository.findBy(userId, longs);
        List<CartDto> cartDtos = cart.stream().map((element) -> modelMapper.map(element, CartDto.class)).collect(Collectors.toList());
        return cartDtos;
    }
}

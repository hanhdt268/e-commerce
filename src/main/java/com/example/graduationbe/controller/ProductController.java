package com.example.graduationbe.controller;


import com.example.graduationbe.dto.CartDto;
import com.example.graduationbe.dto.ProductDto;
import com.example.graduationbe.entities.commerce.*;
import com.example.graduationbe.enums.ProductEnum;
import com.example.graduationbe.repository.AccessoryRepository;
import com.example.graduationbe.repository.LaptopRepository;
import com.example.graduationbe.repository.ProductRepository;
import com.example.graduationbe.repository.SmartPhoneRepository;
import com.example.graduationbe.service.ProductService;
import com.example.graduationbe.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/product")
@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    private final ProductServiceImpl productServiceImpl;
    private final LaptopRepository laptopRepository;
    private final AccessoryRepository accessoryRepository;
    private final SmartPhoneRepository smartPhoneRepository;

    @PostMapping("/")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto
    ) {

        if (!productDto.getProductEnum().equals(ProductEnum.ACCESSORY)
                && !productDto.getProductEnum().equals(ProductEnum.LAPTOP)
                && !productDto.getProductEnum().equals(ProductEnum.SMARTPHONE)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        try {
            Product product = new Product();
            BeanUtils.copyProperties(productDto, product, "pId", "accessoryConfig",
                    "smartPhoneConfig", "laptopConfig", "productEnum");

            Product productSave = this.productService.creteaProduct(product);
            ProductDto productDtoReturn = new ProductDto();
            BeanUtils.copyProperties(productSave, productDtoReturn, "accessoryProd",
                    "smartPhoneProd", "laptop", "productEnum");
            switch (productDto.getProductEnum().toString()) {
                case "LAPTOP" -> {
                    Laptop laptop = productDto.getLaptopConfig();
                    laptop.setLap_id(productSave);
                    Laptop laptopSave = this.laptopRepository.save(laptop);
                    productDtoReturn.setLaptopConfig(laptopSave);
                }
                case "SMARTPHONE" -> {
                    SmartPhone smartPhone = productDto.getSmartPhoneConfig();
                    smartPhone.setSmartId(productSave);
                    SmartPhone smartPhoneSave = this.smartPhoneRepository.save(smartPhone);
                    productDtoReturn.setSmartPhoneConfig(smartPhoneSave);
                }
                case "ACCESSORY" -> {
                    Accessory accessory = productDto.getAccessoryConfig();
                    accessory.setAccessId(productSave);
                    Accessory accessorySave = this.accessoryRepository.save(accessory);
                    productDtoReturn.setAccessoryConfig(accessorySave);
                }
            }
            return ResponseEntity.ok(productDtoReturn);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

//    public Set<ImageModel> uploadFile(MultipartFile[] multipartFiles) throws IOException {
//        Set<ImageModel> imageModels = new HashSet<>();
//        for (MultipartFile file : multipartFiles) {
//            ImageModel imageModel = new ImageModel(
//                    file.getOriginalFilename(),
//                    file.getContentType(),
//                    file.getBytes()
//            );
//            imageModels.add(imageModel);
//        }
//        return imageModels;
//    }

    @PutMapping("/")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto
    ) {
//        return ResponseEntity.ok(this.productService.addProduct(product));
        if (!productDto.getProductEnum().equals(ProductEnum.ACCESSORY)
                && !productDto.getProductEnum().equals(ProductEnum.LAPTOP)
                && !productDto.getProductEnum().equals(ProductEnum.SMARTPHONE)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        try {
            Product product = new Product();
            BeanUtils.copyProperties(productDto, product, "accessoryProd",
                    "smartPhoneProd", "laptop", "productEnum");
            Product productSave = this.productService.creteaProduct(product);
            ProductDto productDtoReturn = new ProductDto();
            BeanUtils.copyProperties(productSave, productDtoReturn, "accessoryProd",
                    "smartPhoneProd", "laptop", "productEnum");
            switch (productDto.getProductEnum().toString()) {
                case "LAPTOP" -> {
                    Laptop laptop = productDto.getLaptopConfig();
                    laptop.setLap_id(productSave);
                    Laptop laptopSave = this.laptopRepository.save(laptop);
                    productDtoReturn.setLaptopConfig(laptopSave);
                }
                case "SMARTPHONE" -> {
                    SmartPhone smartphone = productDto.getSmartPhoneConfig();
                    smartphone.setSmartId(productSave);
                    SmartPhone smartphoneSave = this.smartPhoneRepository.save(smartphone);
                    productDtoReturn.setSmartPhoneConfig(smartphoneSave);
                }
                case "ACCESSORY" -> {
                    Accessory accessory = productDto.getAccessoryConfig();
                    accessory.setAccessId(productSave);
                    Accessory accessorySave = this.accessoryRepository.save(accessory);
                    productDtoReturn.setAccessoryConfig(accessorySave);
                }
            }
            return ResponseEntity.ok(productDtoReturn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("/{pId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("pId") Long pId) {
        return ResponseEntity.ok(this.productService.getProductById(pId));
    }

    //getAll
    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getProduct() {
        return ResponseEntity.ok(this.productService.getProducts());
    }

    //delete
    @DeleteMapping("/{pId}")
    public void deleteProduct(@PathVariable("pId") Long pId) {
        this.productService.deleteProduct(pId);
    }


    //get category by product
    @GetMapping("/category/{cateId}")
    public List<ProductDto> getProductOfCategory(@RequestParam(defaultValue = "0") int pageNumber,
                                                 @RequestParam(defaultValue = "") String searchKey,
                                                 @PathVariable("cateId") Long cateId) {
        Category category = new Category();
        category.setCateId(cateId);
        return this.productService.getProductOfCategory(category, pageNumber, searchKey);
    }

    @GetMapping("/{cateId}/{minPrice}/{maxPrice}")
    public List<ProductDto> getProductBySuggest(@RequestParam(defaultValue = "0") int pageNumber,
                                                @PathVariable("cateId") Long cateId,
                                                @PathVariable("minPrice") Long minPrice,
                                                @PathVariable("maxPrice") Long maxPrice) {
        Category category = new Category();
        category.setCateId(cateId);
        return this.productServiceImpl.getProductBySuggest(category, minPrice, maxPrice, pageNumber);
    }

    @GetMapping("/allProduct/{pid}")
    public List<ProductDto> getCompareProduct(@PathVariable List<Long> pid
    ) {
        List<Product> products = this.productRepository.findBy(pid);
        List<ProductDto> productDtos = products.stream().map((element) ->
                modelMapper.map(element, ProductDto.class)).collect(Collectors.toList());
        return productDtos;
    }


    //get active product
    @GetMapping("/active")
    public List<ProductDto> getActiveProduct(@RequestParam(defaultValue = "0") int pageNumber,
                                             @RequestParam(defaultValue = "") String searchKey
    ) {
        return this.productService.getActiveProduct(pageNumber, searchKey);
    }

    @GetMapping("/all")
    public List<ProductDto> getProducts(
            @RequestParam(defaultValue = "") String searchKey
    ) {
        return this.productServiceImpl.getProducts(searchKey);
    }

    @GetMapping("/testAPi")
    public List<ProductDto> testApi(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String searchKey
    ) {
        return this.productServiceImpl.testApi(searchKey, pageNumber);
    }

    @GetMapping("/selling")
    public List<ProductDto> getProductByBestSelling(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String searchKey
    ) {
        return this.productServiceImpl.getProductByBestSelling(searchKey, pageNumber);
    }

    @GetMapping("/acs")
    public List<ProductDto> getProductByPriceAsc(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String searchKey
    ) {
        return this.productServiceImpl.getProductByPriceAsc(searchKey, pageNumber);
    }

    @GetMapping("/test")
    public List<ProductDto> getProductsTst(

    ) {
        List<Product> products = this.productRepository.findAllBy();
        return products.stream().map((element) -> modelMapper.map(element, ProductDto.class)).collect(Collectors.toList());
    }

    @GetMapping("/manufacturer/{manuId}")
    public List<ProductDto> getProductByManufacturer(@RequestParam(defaultValue = "0") int pageNumber,
                                                     @RequestParam(defaultValue = "") String searchKey,
                                                     @PathVariable("manuId") Long manuId) {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setManuId(manuId);
        return this.productService.getProductOfManufacturer(manufacturer, pageNumber, searchKey);
    }

//    @GetMapping("/{isSingleProductCheckOut}/{pId}")
//    public List<ProductDto> getProductDetails(@PathVariable(name = "isSingleProductCheckOut") boolean isSingleProductCheckOut,
//                                              @PathVariable(name = "pId") Long pId) {
//        return this.productService.getProductDetails(isSingleProductCheckOut, pId);
//    }

    @GetMapping("/get/{userId}/{cartId}")
    public List<CartDto> getProductForPayment(@PathVariable(name = "userId") Long userId,
                                              @PathVariable(name = "cartId") List<Long> cartId) {
        return this.productServiceImpl.getProductForPayment(userId, cartId);
    }
}

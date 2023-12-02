package com.example.graduationbe.dto;

import com.example.graduationbe.entities.commerce.*;
import com.example.graduationbe.enums.ProductEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto implements Serializable {

    private Long pId;

    private String title;
    private String description;
    private String config;
    private Double price;
    private Double discountPrice;
    private String images;
    private Boolean active = false;
    private Integer quantum;
    private Category category;
    private Manufacturer manufacturer;
    private Set<String> imgChildren;
    private Laptop laptopConfig;
    private SmartPhone smartPhoneConfig;
    private Accessory accessoryConfig;
    private ProductEnum productEnum;
    private Set<CommentDto> comment;
    private Set<ReviewDto> reviews;

}

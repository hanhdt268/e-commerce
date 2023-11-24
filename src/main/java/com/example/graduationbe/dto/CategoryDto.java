package com.example.graduationbe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto implements Serializable {
    private Long cateId;
    private String title;
    private Set<ManufacturerDto> manufacturers;
}

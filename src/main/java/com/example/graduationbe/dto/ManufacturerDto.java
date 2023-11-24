package com.example.graduationbe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ManufacturerDto implements Serializable {
    private Long manuId;
    private String title;
    private Boolean completed = false;
}

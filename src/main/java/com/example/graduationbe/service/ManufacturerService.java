package com.example.graduationbe.service;

import com.example.graduationbe.entities.commerce.Category;
import com.example.graduationbe.entities.commerce.Manufacturer;

import java.util.List;

public interface ManufacturerService {
    Manufacturer createManufacturer(Manufacturer manufacturer) throws Exception;

    List<Manufacturer> getAll();

    Manufacturer getManufacturerById(Long manuId);

    Manufacturer updateManufacturer(Manufacturer manufacturer) throws Exception;


    List<Manufacturer> findManufacturerByCategory(Category category);

}

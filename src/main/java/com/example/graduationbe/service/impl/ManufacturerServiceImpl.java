package com.example.graduationbe.service.impl;

import com.example.graduationbe.entities.commerce.Category;
import com.example.graduationbe.entities.commerce.Manufacturer;
import com.example.graduationbe.repository.ManufacturerRepository;
import com.example.graduationbe.service.ManufacturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    @Override
    public Manufacturer createManufacturer(Manufacturer manufacturer) throws Exception {
        Manufacturer manufacturer1 = this.manufacturerRepository.findByTitle(manufacturer.getTitle());
        if (manufacturer1 != null) {
            System.out.println("Category is already there!!!");
            throw new Exception();
        } else {
            manufacturer1 = this.manufacturerRepository.save(manufacturer);
        }
        return manufacturer1;
    }

    @Override
//    @Cacheable("manufacturer")
    public List<Manufacturer> getAll() {
        return this.manufacturerRepository.findAll();
    }

    @Override
//    @Cacheable("manufacturer")
    public Manufacturer getManufacturerById(Long manuId) {
        return this.manufacturerRepository.findById(manuId).orElse(null);
    }

    @Override
    public Manufacturer updateManufacturer(Manufacturer manufacturer) throws Exception {
        Manufacturer manufacturer1 = this.manufacturerRepository.findByTitle(manufacturer.getTitle());
        if (manufacturer1 != null) {
            System.out.println("Category is already there!!!");
            throw new Exception();
        } else {
            manufacturer1 = this.manufacturerRepository.save(manufacturer);
        }
        return manufacturer1;
    }


    @Override
    public List<Manufacturer> findManufacturerByCategory(Category category) {
        return this.manufacturerRepository.findManufacturerByCategory(category);
    }


    public Manufacturer updateSelected(Long manuId) {
        Manufacturer manufacturer = this.manufacturerRepository.findById(manuId).get();
        if (manufacturer == null) {
            throw new UsernameNotFoundException("not found");
        }
        manufacturer.setCompleted(true);
        return this.manufacturerRepository.save(manufacturer);
    }

    public Manufacturer updateSelect(Long manuId) {
        Manufacturer manufacturer = this.manufacturerRepository.findById(manuId).get();
        if (manufacturer == null) {
            throw new UsernameNotFoundException("not found");
        }
        manufacturer.setCompleted(false);
        return this.manufacturerRepository.save(manufacturer);
    }
}

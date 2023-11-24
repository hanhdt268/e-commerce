package com.example.graduationbe.controller;

import com.example.graduationbe.entities.commerce.Category;
import com.example.graduationbe.entities.commerce.Manufacturer;
import com.example.graduationbe.repository.ManufacturerRepository;
import com.example.graduationbe.service.impl.ManufacturerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/manufacturer")
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class ManufacturerController {

    private final ManufacturerServiceImpl manufacturerService;

    private final ManufacturerRepository manufacturerRepository;


    @PostMapping("/")
    public ResponseEntity<Manufacturer> addManufacturer(@RequestBody Manufacturer manufacturer) {
        return ResponseEntity.ok(this.manufacturerService.createManufacturer(manufacturer));
    }


    @GetMapping("/category/{cId}")
    public ResponseEntity<List<Manufacturer>> getManufacturerByCategory(@PathVariable("cId") Long cId) {
        Category category = new Category();
        category.setCateId(cId);
        return ResponseEntity.ok(this.manufacturerService.findManufacturerByCategory(category)
        );
    }

    @PutMapping("/")
    public ResponseEntity<Manufacturer> updateManufacturer(@RequestBody Manufacturer manufacturer) {
        return ResponseEntity.ok(this.manufacturerService.updateManufacturer(manufacturer));
    }

    @GetMapping("/")
    public ResponseEntity<List<Manufacturer>> getAllManufacturer() {
        return ResponseEntity.ok(this.manufacturerService.getAll());
    }

    @GetMapping("/{mId}")
    public ResponseEntity<?> getManufacturerById(@PathVariable("mId") Long mId) {
        return ResponseEntity.ok(this.manufacturerService.getManufacturerById(mId));
    }

    @DeleteMapping("/{manuId}")
    public void deleteManufacturer(@PathVariable("manuId") Long manuId) {
        this.manufacturerRepository.deleteBy(manuId);
    }

    @GetMapping("/selected/{manuId}")
    public ResponseEntity<Manufacturer> updateSelected(@PathVariable("manuId") Long manuId) {
        return ResponseEntity.ok(this.manufacturerService.updateSelected(manuId));

    }


    @GetMapping("/select/{manuId}")
    public ResponseEntity<Manufacturer> updateSelect(@PathVariable("manuId") Long manuId) {
        return ResponseEntity.ok(this.manufacturerService.updateSelect(manuId));
    }

    @DeleteMapping("/delete/{manuId}")
    @CacheEvict(value = "manufacturer", allEntries = true)
    public void delete(@PathVariable List<Long> manuId) {
        this.manufacturerRepository.deleteByIdIn(manuId);
    }
}

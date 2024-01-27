package com.example.graduationbe.repository;

import com.example.graduationbe.entities.commerce.Category;
import com.example.graduationbe.entities.commerce.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

    List<Manufacturer> findManufacturerByCategory(Category category);

    @Modifying
    @Transactional
    @Query(value = "delete from manufacturer where manu_id=:manuId", nativeQuery = true)
    void deleteBy(Long manuId);

    @Modifying
    @Transactional
    @Query(value = "delete  from manufacturer where manu_id in(:longs)", nativeQuery = true)
    void deleteByIdIn(List<Long> longs);

    Manufacturer findByTitle(String title);


}

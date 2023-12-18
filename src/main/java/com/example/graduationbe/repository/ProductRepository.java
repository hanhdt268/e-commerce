package com.example.graduationbe.repository;

import com.example.graduationbe.entities.commerce.Category;
import com.example.graduationbe.entities.commerce.Manufacturer;
import com.example.graduationbe.entities.commerce.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Category category);

    List<Product> findByActive(Boolean b);


    List<Product> findByManufacturerAndActive(Manufacturer manufacturer, Boolean m);


    List<Product> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
            String searchKey1, String searchKey2
    );

    //    @Cacheable(value = "Desc", key = "{#pageable,#title}")
    List<Product> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrderByDiscountPriceDesc
    (String title, String description, Pageable pageable);

    //    @Cacheable(value = "Asc", key = "{#pageable,#title}")
    List<Product> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrderByDiscountPriceAsc
    (String title, String description, Pageable pageable);


    @Query("select p from Order o join OrderDetails od on o.orderId = od.order.orderId inner join Product p on od.product.pId = p.pId " +
            "where p.title like concat('%',:title,'%') or p.description like concat('%',:title,'%') and od.quantity = (select max(od.quantity) from OrderDetails ) group by p.pId order by od.quantity desc")
//    @Cacheable(value = "Selling", key = "{#pageable,#title}")
    List<Product> findByTitleContainingOrDescriptionContainingCaseInsensitive
    (String title, Pageable pageable);


    @Query(value = "select  * from product where category_cate_id=? and price>=(?-3000000) and price<=(?+3000000)", nativeQuery = true)
    List<Product> findByCate(Category category, Double minPrice, Double maxPrice, Pageable pageable);

    @Query(value = "select * from product where p_id in(:longs)", nativeQuery = true)
    List<Product> findBy(List<Long> longs);

    @Query(value = "select p.*, sum(r.value)/count(r.re_id) as start from product p inner join review r\n" +
            "    on p.p_id = r.product_p_id group by p.p_id", nativeQuery = true)
    List<Product> findAllBy();

    List<Product> findByManufacturerOrderByDiscountPrice
            (Manufacturer manufacturer);

    List<Product> findByManufacturerOrderByDiscountPriceDesc
            (Manufacturer manufacturer);

    @Query(value = "select p.* from orders o join order_detail od on o.order_id = od.order_id\n" +
            "    inner join product p on od.product_p_id = p.p_id join manufacturer m on p.manufacturer_manu_id = m.manu_id\n" +
            "  where m.manu_id=? and od.quantity =\n" +
            "                  (select max(od.quantity) from order_detail) group by p.p_id order by od.quantity desc\n", nativeQuery = true)
    List<Product> findBy(Long manuId);

}

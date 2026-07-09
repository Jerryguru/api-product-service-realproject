package com.ar.product.service.realproject.repository;



import com.ar.product.service.realproject.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

/*
 * Here Spring Creates SQL Automatically
 * Insted of Select*,Insert,Update,Delete
 * Here JPARepository provides them all without creating them
 * */
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByProductNameAndCategory(String productName,String category);

    Product findByProductName(String productName);

    List<Product> findByProductNameContaining(String keyword);

    List<Product> findByProductNameContainingIgnoreCase(String keyword);

    Optional<Product> findBySku(String sku);

    List<Product> findByCategory(String category);

    List<Product> findByStatus(Boolean status);

    List<Product> findByStatusTrue();

    List<Product> findByStatusFalse();

    List<Product> findByCategoryAndBrand(String category, String brand); // Finding by Multiple Methods
    List<Product> findByCategoryOrBrand(String category, String brand);
    List<Product> findByCategoryAndStatus(String category, Boolean status);

    /*//  Finding By JPQL Queries


    @Repository
    public interface ProductRepository extends JpaRepository<Product, Long> {

        // Active Products

        @Query("SELECT p FROM Product p WHERE p.status = true")
        List<Product> findActiveProducts();


        // Category

        @Query("SELECT p FROM Product p WHERE p.category = :category")
        List<Product> findProductsByCategory(@Param("category") String category);


        // Brand + Category

        @Query("""
            SELECT p
            FROM Product p
            WHERE p.brand = :brand
            AND p.category = :category
            """)
        List<Product> findProductsByBrandAndCategory(
                @Param("brand") String brand,
                @Param("category") String category);

    }
*/
}


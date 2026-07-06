package com.ar.product.service.realproject.repository;



import com.ar.product.service.realproject.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * Here Spring Creates SQL Automatically
 * Insted of Select*,Insert,Update,Delete
 * Here JPARepository provides them all without creating them
 * */
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByProductNameAndCategory(String productName,String category);

}


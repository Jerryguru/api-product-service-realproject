package com.ar.product.service.realproject.service;



import com.ar.product.service.realproject.entity.Product;

import java.util.List;

public interface ProductService {


    Product updateProduct(Long id, Product product);


    Product saveProduct(Product product);

    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product partiallyUpdateProduct(Long id, Product product);

    String deleteById(Long id);

    List<Product> getAllProductByProductName(String productName,String category);
}

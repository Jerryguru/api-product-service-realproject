package com.ar.product.service.realproject.service;



import com.ar.product.service.realproject.entity.Product;
import com.ar.product.service.realproject.model.ProductRequest;
import com.ar.product.service.realproject.model.ProductResponse;

import java.util.List;

public interface ProductService {


  //  Product updateProduct(Long id, Product product);
  ProductResponse updateProduct(Long id, ProductRequest request);

   // Product saveProduct(Product product);
   ProductResponse saveProduct(ProductRequest request);

  //  List<Product> getAllProducts();
  List<ProductResponse> getAllProducts();

   // Product getProductById(Long id);
   ProductResponse getProductById(Long id);

   // Product partiallyUpdateProduct(Long id, Product product);
   ProductResponse partiallyUpdateProduct(Long id, ProductRequest request);

  //  String deleteById(Long id);
  String deleteById(Long id);

    List<Product> getAllProductByProductName(String productName,String category);
}

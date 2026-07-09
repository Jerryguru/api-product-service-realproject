
package com.ar.product.service.realproject.service;

import com.ar.product.service.realproject.entity.Product;
import com.ar.product.service.realproject.model.ProductRequest;
import com.ar.product.service.realproject.model.ProductResponse;

import java.util.List;

public interface ProductService {

    // Create Product
    ProductResponse saveProduct(ProductRequest request);

    // Get All Products
    List<ProductResponse> getAllProducts();

    // Get Product By Id
    ProductResponse getProductById(Long id);

    // Update Complete Product (PUT)
    ProductResponse updateProduct(Long id, ProductRequest request);

    // Update Partial Product (PATCH)
    ProductResponse partiallyUpdateProduct(Long id, ProductRequest request);

    // Delete Product
    String deleteById(Long id);

    // Search Products
    List<Product> getAllProductByProductName(String productName, String category);
    ProductResponse getProductByProductName(String productName);
    List<ProductResponse> searchProductByKeyword(String keyword);
    List<ProductResponse> searchProductByKeywordIgnoreCase(String keyword);
    ProductResponse getProductBySku(String sku);
    List<ProductResponse> getProductsByCategory(String category);
    List<ProductResponse> getProductsByStatus(Boolean status);
    List<ProductResponse> getActiveProducts();
    List<ProductResponse> getInactiveProducts();

    List<ProductResponse> getProductsByCategoryAndBrand(String category,
                                                        String brand); // Finding By Multiple Methods

    List<ProductResponse> getProductsByCategoryOrBrand(String category,
                                                       String brand);

    List<ProductResponse> getProductsByCategoryAndStatus(String category,
                                                         Boolean status);

    //  Finding By JPQL Queries

   /* List<ProductResponse> getActiveProducts();

    List<ProductResponse> getProductsByCategory(String category);

    List<ProductResponse> getProductsByBrandAndCategory(
            String brand,
            String category);*/

    boolean updateStock(Long id, int quantity);
}
















/*
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
*/

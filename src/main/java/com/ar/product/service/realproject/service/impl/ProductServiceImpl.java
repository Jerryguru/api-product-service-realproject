
package com.ar.product.service.realproject.service.impl;

import com.ar.product.service.realproject.entity.Product;
import com.ar.product.service.realproject.exception.ProductNotFoundException;
import com.ar.product.service.realproject.model.ProductRequest;
import com.ar.product.service.realproject.model.ProductResponse;
import com.ar.product.service.realproject.repository.ProductRepository;
import com.ar.product.service.realproject.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponse saveProduct(ProductRequest request) {
        log.info("Saving product : {}", request.getProductName());
        Product product = mapRequestToEntity(request);
        Product savedProduct = productRepository.save(product);
        log.info("Product saved successfully with id : {}", savedProduct.getId());

        return mapEntityToResponse(savedProduct);
    }


    @Override
    public List<ProductResponse> getAllProducts() {
        log.info("Fetching all products");
        List<Product> products = productRepository.findAll();
        log.info("Found {} products", products.size());

        return products.stream()
                .map(this::mapEntityToResponse)
                .toList();
    }

    @Override
    public ProductResponse getProductById(Long id) {
        log.info("Fetching product with id : {}", id);
        Product product = getProduct(id);
        log.info("Product fetched successfully with id : {}", id);

        return mapEntityToResponse(product);
    }


    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        log.info("Updating product with id : {}", id);
        Product product = getProduct(id);

        updateEntityFromRequest(product, request);

        Product updatedProduct = productRepository.save(product);
        log.info("Product updated successfully with id : {}", id);

        return mapEntityToResponse(updatedProduct);
    }

    @Override
    public ProductResponse partiallyUpdateProduct(Long id, ProductRequest request) {
        log.info("Partially updating product with id : {}", id);
        Product product = getProduct(id);

        partialUpdateEntity(product, request);

        Product updatedProduct = productRepository.save(product);
        log.info("Product partially updated successfully with id : {}", id);
        return mapEntityToResponse(updatedProduct);
    }


    @Override
    public String deleteById(Long id) {
        log.info("Deleting product with id : {}", id);
        Product product = getProduct(id);

        productRepository.delete(product);
        log.info("Product deleted successfully with id : {}", id);
        return "Product deleted successfully.";
    }

    @Override
    public List<Product> getAllProductByProductName(String productName, String category) {
        log.info("Searching products by name : {} and category : {}", productName, category);
        List<Product> products =
                productRepository.findByProductNameAndCategory(productName, category);

        log.info("Found {} matching products", products.size());
        return productRepository.findByProductNameAndCategory(productName, category);
    }

    @Override
    public ProductResponse getProductByProductName(String productName) {

        log.info("Searching product by name : {}", productName);

        Product product =
                productRepository.findByProductName(productName);

        return mapEntityToResponse(product);
    }

    @Override
    public List<ProductResponse> searchProductByKeyword(String keyword) {

        log.info("Searching products containing keyword : {}", keyword);

        List<Product> products =
                productRepository.findByProductNameContaining(keyword);

        log.info("Found {} matching products", products.size());

        return products.stream()
                .map(this::mapEntityToResponse)
                .toList();
    }


    @Override
    public List<ProductResponse> searchProductByKeywordIgnoreCase(String keyword) {

        log.info("Searching products containing keyword (Ignore Case): {}", keyword);

        List<Product> products =
                productRepository.findByProductNameContainingIgnoreCase(keyword);

        log.info("Found {} matching products", products.size());

        return products.stream()
                .map(this::mapEntityToResponse)
                .toList();
    }

    @Override
    public ProductResponse getProductBySku(String sku) {

        log.info("Searching product by SKU : {}", sku);

        Product product = productRepository.findBySku(sku)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found with SKU : " + sku));

        return mapEntityToResponse(product);
    }

    @Override
    public List<ProductResponse> getProductsByCategory(String category) {

        log.info("Searching products by category : {}", category);

        List<Product> products =
                productRepository.findByCategory(category);

        log.info("Found {} products", products.size());

        return products.stream()
                .map(this::mapEntityToResponse)
                .toList();
    }


    @Override
    public List<ProductResponse> getProductsByStatus(Boolean status) {

        log.info("Searching products by status : {}", status);

        List<Product> products =
                productRepository.findByStatus(status);

        log.info("Found {} products", products.size());

        return products.stream()
                .map(this::mapEntityToResponse)
                .toList();
    }

    @Override
    public List<ProductResponse> getActiveProducts() {

        log.info("Fetching active products");

        List<Product> products =
                productRepository.findByStatusTrue();

        log.info("Found {} active products", products.size());

        return products.stream()
                .map(this::mapEntityToResponse)
                .toList();
    }

    @Override
    public List<ProductResponse> getInactiveProducts() {

        log.info("Fetching inactive products");

        List<Product> products =
                productRepository.findByStatusFalse();

        log.info("Found {} inactive products", products.size());

        return products.stream()
                .map(this::mapEntityToResponse)
                .toList();
    }

    // Finding By Multiple Methods

    @Override
    public List<ProductResponse> getProductsByCategoryAndBrand(
            String category,
            String brand) {

        log.info("Searching products by category : {} and brand : {}",
                category, brand);

        List<Product> products =
                productRepository.findByCategoryAndBrand(category, brand);

        log.info("Found {} matching products", products.size());

        return products.stream()
                .map(this::mapEntityToResponse)
                .toList();
    }

    @Override
    public List<ProductResponse> getProductsByCategoryOrBrand(
            String category,
            String brand) {

        log.info("Searching products by category : {} OR brand : {}",
                category, brand);

        List<Product> products =
                productRepository.findByCategoryOrBrand(category, brand);

        log.info("Found {} matching products", products.size());

        return products.stream()
                .map(this::mapEntityToResponse)
                .toList();
    }


    @Override
    public List<ProductResponse> getProductsByCategoryAndStatus(
            String category,
            Boolean status) {

        log.info("Searching products by category : {} and status : {}",
                category, status);

        List<Product> products =
                productRepository.findByCategoryAndStatus(category, status);

        log.info("Found {} matching products", products.size());

        return products.stream()
                .map(this::mapEntityToResponse)
                .toList();
    }

    @Override
    public boolean updateStock(Long id, int quantity) {
        log.info("Updating Stock for Product {}: quantity{}",id,quantity);
        // now Fething the data with ID here
      Product product =  productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found with Id "+id));
      // now update the stock now i need to
        int newStock = product.getQuantity()+ quantity;
        if (newStock < 0){
            log.warn("Insufficient stock for Product {} : quantity {}",id,quantity);
            return  false;
        }
        product.setQuantity(newStock);
        productRepository.save(product);
        log.info("Product updated. New Stock:{}",newStock);
        return true;
    }


    // ===========================
    // Private Helper Methods
    // ===========================

   /* private Product getProduct(Long id) {

        return productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found with id " + id));
    }*/

    private Product getProduct(Long id) {
        log.debug("Searching product in database with id : {}", id);
        return productRepository.findById(id)
                .orElseThrow(() ->{
                    log.error("Product not found with id : {}", id);
                    return     new ProductNotFoundException("Product not found with ID: " + id);});
    }

    private Product mapRequestToEntity(ProductRequest request) {

        return Product.builder()
                .productName(request.getProductName())
                .description(request.getDescription())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .category(request.getCategory())
                .brand(request.getBrand())
                .sku(request.getSku())
                .status(request.getStatus())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
    }

    private ProductResponse mapEntityToResponse(Product product) {

        return ProductResponse.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .category(product.getCategory())
                .brand(product.getBrand())
                .sku(product.getSku())
                .status(product.getStatus())
                .createDate(product.getCreatedDate())
                .updateDate(product.getUpdatedDate())
                .build();
    }

    private void updateEntityFromRequest(Product product, ProductRequest request) {

        product.setProductName(request.getProductName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setCategory(request.getCategory());
        product.setBrand(request.getBrand());
        product.setSku(request.getSku());
        product.setStatus(request.getStatus());
        product.setUpdatedDate(LocalDateTime.now());
    }

    private void partialUpdateEntity(Product product, ProductRequest request) {

        if (request.getProductName() != null) {
            product.setProductName(request.getProductName());
        }

        if (request.getDescription() != null) {
            product.setDescription(request.getDescription());
        }

        if (request.getPrice() != null) {
            product.setPrice(request.getPrice());
        }

        if (request.getQuantity() != null) {
            product.setQuantity(request.getQuantity());
        }

        if (request.getCategory() != null) {
            product.setCategory(request.getCategory());
        }

        if (request.getBrand() != null) {
            product.setBrand(request.getBrand());
        }

        if (request.getSku() != null) {
            product.setSku(request.getSku());
        }

        if (request.getStatus() != null) {
            product.setStatus(request.getStatus());
        }

        product.setUpdatedDate(LocalDateTime.now());
    }




}








































/*
package com.ar.product.service.realproject.service.impl;


import com.ar.product.service.realproject.entity.Product;
import com.ar.product.service.realproject.model.ProductRequest;
import com.ar.product.service.realproject.model.ProductResponse;
import com.ar.product.service.realproject.repository.ProductRepository;
import com.ar.product.service.realproject.service.ProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

// Here we Write the Business Logics
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

  */
/*  @Override
    public Product saveProduct(Product product) {
        product.setCreatedDate (LocalDateTime.now());
        product.setUpdatedDate(LocalDateTime.now());
        Product saveProduct =   productRepository.save(product);
        return saveProduct;
    }*//*


    @Override
    public ProductResponse saveProduct(ProductRequest request) {
// Creating the Product Here & here return type is entity only
 Product product = Product.builder()
        .productName(request.getProductName())
        .description(request.getDescription())
        .price(request.getPrice())
        .quantity(request.getQuantity())
        .category(request.getCategory())
        .brand(request.getBrand())
        .sku(request.getSku())
        .status(request.getStatus())
        .build();

  Product saveProduct = productRepository.save(product);
  ProductResponse response =  mapEntityToResponse(saveProduct);
  return response;
  //return mapEntityToResponse(saveProduct);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
       // List<Product> products =  productRepository.findAll();
        List<Product> products = productRepository.findAll();

        List<ProductResponse> responses = products.stream()
                .map(product -> ProductResponse.builder()
                        .id(product.getId())
                        .productName(product.getProductName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .quantity(product.getQuantity())
                        .category(product.getCategory())
                        .brand(product.getBrand())
                        .sku(product.getSku())
                        .status(product.getStatus())
                        .createDate(product.getCreatedDate())
                        .updateDate(product.getUpdatedDate())
                        .build())
                .toList();

        return responses;
    }

    */
/* @Override
     public Product getProductById(Long id) {
         return productRepository.findById(id)
                 .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
     }*//*

 */
/*   @Override
    public Product getProductById(Long id) {
        Product product =  productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Product not found with id "+id));
        // .orElseThrow(new RuntimeException("No Product found"));
        return product;
    }*//*

    @Override
    public ProductResponse getProductById(Long id) {

        // Fetch Entity from Database
        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found with id " + id));

        // Convert Entity to Response DTO
        ProductResponse response = ProductResponse.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .category(product.getCategory())
                .brand(product.getBrand())
                .sku(product.getSku())
                .status(product.getStatus())
                .createDate(product.getCreatedDate())
                .updateDate(product.getUpdatedDate())
                .build();

        // Return Response DTO
        return response;
    }

   */
/* @Override
    public Product updateProduct(Long id, Product product) {
        // Get the Data From the DB
        Product productResp =  productRepository.findById(id).orElseThrow(()->new
                RuntimeException("Product Not Fount With Id"+id));*//*


    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found with id " + id));

        product.setProductName(request.getProductName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setCategory(request.getCategory());
        product.setBrand(request.getBrand());
        product.setSku(request.getSku());
        product.setStatus(request.getStatus());
        product.setUpdatedDate(LocalDateTime.now());

        Product updatedProduct = productRepository.save(product);

        return ProductResponse.builder()
                .id(updatedProduct.getId())
                .productName(updatedProduct.getProductName())
                .description(updatedProduct.getDescription())
                .price(updatedProduct.getPrice())
                .quantity(updatedProduct.getQuantity())
                .category(updatedProduct.getCategory())
                .brand(updatedProduct.getBrand())
                .sku(updatedProduct.getSku())
                .status(updatedProduct.getStatus())
                .createDate(updatedProduct.getCreatedDate())
                .updateDate(updatedProduct.getUpdatedDate())
                .build();
    }

        // Now I need to Update The Product(Entire  Resource)

       */
/* productResp.setProductName(product.getProductName());
        productResp.setDescription(product.getDescription());
        productResp.setPrice(product.getPrice());
        productResp.setCategory(product.getCategory());
        productResp.setQuantity(product.getQuantity());
        productResp.setBrand(product.getBrand());
        productResp.setSku(product.getSku());
        productResp.setStatus(product.getStatus());
       // productResp.setCreatedDate(product.getCreatedDate());
        productResp.setUpdatedDate(LocalDateTime.now());


        return productRepository.save(productResp);
    }*//*



   */
/* @Override
    public Product partiallyUpdateProduct(Long id, Product product) {
        // First We Need to Get Data From The DB
        Product productResp =  productRepository.findById(id).orElseThrow(()->new
                RuntimeException("Product Not Fount With Id"+id));
        // Now We need To Change some modifications like Price,Quantity Like we want to update
        productResp.setPrice(product.getPrice());
        productResp.setQuantity(product.getQuantity());

        return productRepository.save(productResp);
    }*//*


    @Override
    public ProductResponse partiallyUpdateProduct(Long id, ProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found with id " + id));

        // Update only if value is present

        if (request.getPrice() != null) {
            product.setPrice(request.getPrice());
        }

        if (request.getQuantity() != null) {
            product.setQuantity(request.getQuantity());
        }

        if (request.getProductName() != null) {
            product.setProductName(request.getProductName());
        }

        if (request.getDescription() != null) {
            product.setDescription(request.getDescription());
        }

        if (request.getCategory() != null) {
            product.setCategory(request.getCategory());
        }

        if (request.getBrand() != null) {
            product.setBrand(request.getBrand());
        }

        if (request.getSku() != null) {
            product.setSku(request.getSku());
        }

        if (request.getStatus() != null) {
            product.setStatus(request.getStatus());
        }

        product.setUpdatedDate(LocalDateTime.now());

        Product updatedProduct = productRepository.save(product);

        return mapEntityToResponse(updatedProduct);
    }

   */
/* @Override
    public String deleteById(Long id) {
        // if we want to delete the data first you need to get the data from DB
        Product productResp =  productRepository.findById(id).orElseThrow(()->new
                RuntimeException("Product Not Fount With Id"+id));
        productRepository.delete(productResp);

        return "Product has been Deleted";
    }*//*


    @Override
    public String deleteById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found with id " + id));

        productRepository.delete(product);

        return "Product deleted successfully.";
    }

    @Override
    public List<Product> getAllProductByProductName(String productName,String category) {
        List<Product> products = productRepository.findByProductNameAndCategory(productName,category);
        return products;
    }


    // here We Are Writing a Private Method(Common Method) It will gives the Response

private  ProductResponse mapEntityToResponse (Product product){
       // Here Creating the object for what we have heree a returnType
    ProductResponse response = new ProductResponse();

    response.setId(product.getId());
    response.setProductName(product.getProductName());
    response.setDescription(product.getDescription());
    response.setPrice(product.getPrice());
    response.setCategory(product.getCategory());
    response.setQuantity(product.getQuantity());
    response.setBrand(product.getBrand());
    response.setSku(product.getSku());
    response.setStatus(product.getStatus());
    response.setCreateDate(product.getCreatedDate());
    response.setUpdateDate(LocalDateTime.now());

    return response;
}



}



*/

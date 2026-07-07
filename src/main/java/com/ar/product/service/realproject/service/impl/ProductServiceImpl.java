
package com.ar.product.service.realproject.service.impl;

import com.ar.product.service.realproject.entity.Product;
import com.ar.product.service.realproject.model.ProductRequest;
import com.ar.product.service.realproject.model.ProductResponse;
import com.ar.product.service.realproject.repository.ProductRepository;
import com.ar.product.service.realproject.service.ProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponse saveProduct(ProductRequest request) {

        Product product = mapRequestToEntity(request);

        Product savedProduct = productRepository.save(product);

        return mapEntityToResponse(savedProduct);
    }


    @Override
    public List<ProductResponse> getAllProducts() {

        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(this::mapEntityToResponse)
                .toList();
    }

    @Override
    public ProductResponse getProductById(Long id) {

        Product product = getProduct(id);

        return mapEntityToResponse(product);
    }


    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {

        Product product = getProduct(id);

        updateEntityFromRequest(product, request);

        Product updatedProduct = productRepository.save(product);

        return mapEntityToResponse(updatedProduct);
    }

    @Override
    public ProductResponse partiallyUpdateProduct(Long id, ProductRequest request) {

        Product product = getProduct(id);

        partialUpdateEntity(product, request);

        Product updatedProduct = productRepository.save(product);

        return mapEntityToResponse(updatedProduct);
    }


    @Override
    public String deleteById(Long id) {

        Product product = getProduct(id);

        productRepository.delete(product);

        return "Product deleted successfully.";
    }

    @Override
    public List<Product> getAllProductByProductName(String productName, String category) {

        return productRepository.findByProductNameAndCategory(productName, category);
    }


    // ===========================
    // Private Helper Methods
    // ===========================

    private Product getProduct(Long id) {

        return productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found with id " + id));
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

package com.ar.product.service.realproject.service.impl;


import com.ar.product.service.realproject.entity.Product;
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

    @Override
    public Product saveProduct(Product product) {
        product.setCreatedDate (LocalDateTime.now());
        product.setUpdatedDate(LocalDateTime.now());
        Product saveProduct =   productRepository.save(product);
        return saveProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products =  productRepository.findAll();
        return products;
    }

    /* @Override
     public Product getProductById(Long id) {
         return productRepository.findById(id)
                 .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
     }*/
    @Override
    public Product getProductById(Long id) {
        Product product =  productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Product not found with id "+id));
        // .orElseThrow(new RuntimeException("No Product found"));
        return product;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        // Get the Data From the DB
        Product productResp =  productRepository.findById(id).orElseThrow(()->new
                RuntimeException("Product Not Fount With Id"+id));
        // Now I need to Update The Product(Entire  Resource)
        productResp.setProductName(product.getProductName());
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
    }


    @Override
    public Product partiallyUpdateProduct(Long id, Product product) {
        // First We Need to Get Data From The DB
        Product productResp =  productRepository.findById(id).orElseThrow(()->new
                RuntimeException("Product Not Fount With Id"+id));
        // Now We need To Change some modifications like Price,Quantity Like we want to update
        productResp.setPrice(product.getPrice());
        productResp.setQuantity(product.getQuantity());


        return productRepository.save(productResp);
    }

    @Override
    public String deleteById(Long id) {
        // if we want to delete the data first you need to get the data from DB
        Product productResp =  productRepository.findById(id).orElseThrow(()->new
                RuntimeException("Product Not Fount With Id"+id));
        productRepository.delete(productResp);

        return "Product has been Deleted";
    }

    @Override
    public List<Product> getAllProductByProductName(String productName,String category) {
        List<Product> products = productRepository.findByProductNameAndCategory(productName,category);
        return products;
    }


}




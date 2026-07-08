package com.ar.product.service.realproject.controller;

import com.ar.product.service.realproject.entity.Product;
import com.ar.product.service.realproject.model.ProductRequest;
import com.ar.product.service.realproject.model.ProductResponse;
import com.ar.product.service.realproject.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {

   // private  static final  Looger looger = LoogerFactory.getLooger(ProductController.class);

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // ==========================
    // Create Product
    // ==========================

    @PostMapping("/addProduct")
    public ResponseEntity<ProductResponse> saveProduct(
            @RequestBody ProductRequest request) {

    log.info("Recevied request to create product: {}",request.getProductName());
        ProductResponse response = productService.saveProduct(request);
    log.info("Returningcreated  product  with id : {}",response.getId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // ==========================
    // Get All Products
    // ==========================

    @GetMapping("/allProducts")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        log.info("Resived request  to featch all  products");

        List<ProductResponse> response = productService.getAllProducts();
        log.info("Returning {} products",response.size());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // ==========================
    // Get Product By Id
    // ==========================

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        log.info("Received request to fetch product with id : {}", id);

        ProductResponse response = productService.getProductById(id);
        log.info("Returning product with id : {}", id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // ==========================
    // Update Product (PUT)
    // ==========================

    @PutMapping("/{id}/updateProduct")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductRequest request) {
        log.info("Received request to update product with id : {}", id);
        ProductResponse response = productService.updateProduct(id, request);
        log.info("Product updated successfully with id : {}", id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // ==========================
    // Partial Update Product (PATCH)
    // ==========================

    @PatchMapping("/{id}/updateProduct")
    public ResponseEntity<ProductResponse> partiallyUpdateProduct(
            @PathVariable Long id,
            @RequestBody ProductRequest request) {
        log.info("Received request to partially update product with id : {}", id);
        ProductResponse response =
                productService.partiallyUpdateProduct(id, request);
        log.info("Product partially updated with id : {}", id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // ==========================
    // Delete Product
    // ==========================

    @DeleteMapping("/{id}/deleteProduct")
    public ResponseEntity<String> deleteProduct(
            @PathVariable Long id) {
        log.info("Received request to delete product with id : {}", id);
        String response = productService.deleteById(id);
        log.info("Product deleted successfully with id : {}", id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // ==========================
    // Search Product
    // ==========================

    @GetMapping("/name")
    public ResponseEntity<List<Product>> getAllProductByProductName(
            @RequestParam String productName,
            @RequestParam String category) {
        log.info("Received request to search products by name : {} and category : {}", productName, category);
        List<Product> products =
                productService.getAllProductByProductName(productName, category);
        log.info("Found {} matching products", products.size());

        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    // ==========================
    // Update Stock
    // ==========================
    @PutMapping("/{id}/stock")
    public ResponseEntity<String> updateStock(@PathVariable Long id,
                                              @RequestParam int quantity) {

        log.info("Received request to update stock for product id : {} with quantity : {}", id, quantity);

        boolean success = productService.updateStock(id, quantity);

        if (success) {
            log.info("Stock updated successfully for product id : {}", id);
            return ResponseEntity.ok("Stock updated successfully");
        } else {
            log.warn("Stock update failed for product id : {} due to insufficient stock", id);
            return ResponseEntity.badRequest().body("Insufficient Stock");
        }
    }

}





































/*
package com.ar.product.service.realproject.controller;



import com.ar.product.service.realproject.entity.Product;
import com.ar.product.service.realproject.model.ProductRequest;
import com.ar.product.service.realproject.model.ProductResponse;
import com.ar.product.service.realproject.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Here We are going to expose the End Points (Rest APIS)@RestController   // it is going to handels the RestApi Request and return the data that data is usually Jason Data
@RestController
@RequestMapping("/products") // here it is a  defines the Base URl for all APIS in this cotroller
public class ProductController {

    private ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //Insert  the data into the DB
    //Add HTTP Method : POSTMAPPING
   */
/* @PostMapping("/addProduct")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        Product productResp = productService.saveProduct(product);
        return new ResponseEntity<>(productResp, HttpStatus.CREATED);
    }*//*


    @PostMapping("/addProduct")
    public ResponseEntity<ProductResponse> saveProduct(@RequestBody ProductRequest request) {
        ProductResponse productResp = productService.saveProduct(request);
        return new ResponseEntity<>(productResp, HttpStatus.CREATED);
    }

    // Retrive all the records from DB
  */
/*  @GetMapping("/allProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }*//*


    @GetMapping("/allProducts")
    public ResponseEntity<List<ProductResponse>>getAllProducts(){
        List<ProductResponse> response =productService.getAllProducts();
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }

    // Based on the id u need to get the data
  */
/*  @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        Product resp = productService.getProductById(id);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }*//*


    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id){
        ProductResponse response = productService.getProductById(id);
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }



    // PutMapping it is used to Update an Entire Resorse
 */
/*   @PutMapping("/{id}/updateProduct")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id,
                                                 @RequestBody Product product) {
        Product updateProduct = productService.updateProduct(id, product);
        return new ResponseEntity<>(updateProduct, HttpStatus.CREATED);

    }*//*


    @PutMapping("/{id}/updateProduct")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("id")Long id,
                                                         @RequestBody ProductRequest request){
        ProductResponse response = productService.updateProduct(id,request);
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }

    // PatchMapping it is going to Partially Update a Resorse (Particular Data What We want to update )
   */
/* @PatchMapping("{id}/partialUpdate")
    public ResponseEntity<Product> partiallyUpdateProduct(@PathVariable("id") Long id,
                                                          @RequestBody Product product) {
        Product productResp = productService.partiallyUpdateProduct(id, product);
        return new ResponseEntity<>(productResp, HttpStatus.CREATED);
    }*//*


    @PatchMapping("/{id}/updateProduct")
    public ResponseEntity<ProductResponse> partiallyUpdateProduct(
            @PathVariable Long id,
            @RequestBody ProductRequest request) {

        ProductResponse response = productService.partiallyUpdateProduct(id, request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // if We Want to Delete Any Record  in the DB Level Then we will use the DeleteMapping
  */
/*  @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id){
        String delete =   productService.deleteById(id);
        return  new ResponseEntity<>(delete , HttpStatus.OK);
    }*//*


    @DeleteMapping("/{id}/deleteProduct")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {

        String response = productService.deleteById(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Now if we want to search by Name This is the proces now
   */
/* @GetMapping("/name/{productName}")
    public  ResponseEntity<List<Product>>
                                    getAllProductByProductName(@PathVariable String productName){
       List<Product> products= productService.getAllProductByProductName(productName);
        return new ResponseEntity<>(products , HttpStatus.OK);
    }*//*


    @GetMapping("/name")
    public  ResponseEntity<List<Product>>
    getAllProductByProductName(
            @RequestParam("productName") String productName,
            @RequestParam("category") String category)
    {
        List<Product> products= productService.getAllProductByProductName(productName,category);
        return new ResponseEntity<>(products , HttpStatus.OK);
    }


}

*/

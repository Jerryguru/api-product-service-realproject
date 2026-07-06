package com.ar.product.service.realproject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // it is a Lombook Anotation That automatically generates the boiler plate code such as Gettters,Setters,ToString Method,equals , hashcode
@Builder
public class ProductRequest {
    private String productName;
    private String description;
    private Double price;
    private Integer quantity;
    private String category;
    private String brand;
    private String sku;
    private Boolean status;
}

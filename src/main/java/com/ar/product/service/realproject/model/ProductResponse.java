package com.ar.product.service.realproject.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class ProductResponse {
    private Long id;
    private String productName;
    private String description;
    private Double price;
    private Integer quantity;
    private String category;
    private String brand;
    private String sku;
    private Boolean status;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}

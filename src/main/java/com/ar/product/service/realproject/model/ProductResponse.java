package com.ar.product.service.realproject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

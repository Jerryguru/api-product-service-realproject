package com.ar.product.service.realproject.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

//import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
@Entity
@Table(name="products")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // it will gives the auto increment

    private Long id;

    @Column(nullable = false) // here it should not be null mush and should be value.
    private String productName;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private Double price;

    private  Integer quantity;

    private  String category;

    private  String brand;

    @Column(unique = true) // here we cant use the o Duplicates
    private String sku;

    private Boolean status;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private  LocalDateTime updatedDate;



}





//spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

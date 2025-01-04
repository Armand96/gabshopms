package com.gaboot.gabshop.product.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "products")
@ToString
@Accessors(chain = true)
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 100)
    private String name;

    @Column(length = 10)
    private String sku;

    @Column(length = 10)
    private String unit;

    private Double price;
    private Double stock;

    @Column(nullable = true)
    private String defaultImage;

    @Column(nullable = true)
    private String defaultImageThumb;
    private Date createdAt;
    private Date updatedAt;
    private Boolean isActive;
}

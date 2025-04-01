package com.heng.microservice.models;

import jakarta.persistence.*;
import com.heng.microservice.models.Category;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;


@Setter
@Getter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String brand;
    private String description;
    private int inventory;
    private BigDecimal price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    public Product(String name, String brand, String description, int inventory, BigDecimal price, Category category) {
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.inventory = inventory;
        this.price = price;
        this.category = category;
    }

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)

    private List<Image> image;


}

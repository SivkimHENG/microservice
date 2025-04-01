package com.heng.microservice.repository;

import com.heng.microservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository  extends JpaRepository<Product, String> {

    List<Product> findByCategoryName(String category);

    List<Product> findByBrandName(String brand);
    List<Product> findByCategoryNameAndBrandName(String category, String brand);
    List<Product> findByName(String name);

    List<Product> findByBrandAndName(String brand, String name);

    Long countByBrandAndName(String brand, String name);

    Optional<Product> findById(Long id);
}
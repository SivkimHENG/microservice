package com.heng.microservice.repository;

import com.heng.microservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository  extends JpaRepository<Category,Long> {


    Category findByName(String name);
    boolean existsByName(String name);



}




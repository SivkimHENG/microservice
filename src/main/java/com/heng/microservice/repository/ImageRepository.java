package com.heng.microservice.repository;

import com.heng.microservice.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image,Long> {

    List<Image> findByProductId(Long id);





}

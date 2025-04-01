package com.heng.microservice.request;


import com.heng.microservice.models.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddProductRequest {


    private Long id;
    private String name;
    private String description;
    private String brand;
    private int inventory;
    private BigDecimal price;
    private Category category;


}

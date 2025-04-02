package com.heng.microservice.service.category;

import com.heng.microservice.models.Category;

import java.util.List;

public interface ICategoryService {

    Category getCategoryById(Long id);
    Category getCategoryByName(String name);
    List<Category> getAllCategories();
    Category addCategory(Category category);


    Category updateCategory(Category category, Long Id);
    void deleteCategoryById(Long id);

}

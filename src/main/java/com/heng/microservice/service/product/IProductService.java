package com.heng.microservice.service.product;

import com.heng.microservice.models.Product;
import com.heng.microservice.request.AddProductRequest;
import com.heng.microservice.request.ProductUpdateRequest;

import java.util.List;

public interface IProductService {

    Product addProduct(AddProductRequest product);

    Product getProductById(Long id);

    void deleteProductById(Long id);
    Product updateProduct(ProductUpdateRequest request, Long productId);

    List<Product> getAllProducts();
    List<Product> getProductByCategory(String category);
    List<Product> getProductByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String brandName, String name);
    Long countProductsByBrandAndName(String brand, String name);



}

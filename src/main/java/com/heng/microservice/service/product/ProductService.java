package com.heng.microservice.service.product;

import com.heng.microservice.exception.ResourceNotFoundException;
import com.heng.microservice.request.AddProductRequest;
import com.heng.microservice.exception.ProductNotFoundException;
import com.heng.microservice.models.Product;
import com.heng.microservice.repository.CategoryRepository;
import com.heng.microservice.repository.ProductRepository;
import com.heng.microservice.request.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import com.heng.microservice.models.Category;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {


    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;




    private Product createProduct(AddProductRequest request, Category category) {
        return new Product(
                request.getName(),
                request.getBrand(),
                request.getDescription(),
                request.getInventory(),
                request.getPrice(),
                category
        );

    }
    @Override
    public Product addProduct(AddProductRequest request) {
        Category category  = Optional.
                ofNullable(categoryRepository.
                findByName(request.getCategory().getName())).orElseGet(() -> {
                    Category newCategory = new Category(request.getCategory().getName());
                    return  categoryRepository.save(newCategory);
                });
        request.setCategory(category);
        return productRepository.save(createProduct(request,category));
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(String.valueOf(id)).orElseThrow(() -> new ProductNotFoundException(" Product Not Found"));
    }


    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id)
                .ifPresentOrElse(productRepository::delete,
                        () -> {throw new ResourceNotFoundException("Product not found!");});
    }

    @Override
    public Product updateProduct(ProductUpdateRequest request, Long productId) {
        return productRepository.findById(productId)
                .map(existingProduct -> updateExistingProduct(existingProduct,request))
                .map(productRepository :: save)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found"));
    }


    public Product updateExistingProduct(Product existingProduct, ProductUpdateRequest request){
        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setDescription(request.getDescription());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setInventory(request.getInventory());


        Category category = categoryRepository.findByName(request.getCategory().getName());
        existingProduct.setCategory(category);

        return existingProduct;

    }
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }
    @Override
    public List<Product> getProductByBrand(String brand) {
        return productRepository.findByBrandName(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrandName(category,brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }
}

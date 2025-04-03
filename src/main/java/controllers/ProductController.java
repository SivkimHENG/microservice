package controllers;


import com.heng.microservice.exception.AlreadyExistsException;
import com.heng.microservice.exception.ResourceNotFoundException;
import com.heng.microservice.models.Product;
import com.heng.microservice.request.AddProductRequest;
import com.heng.microservice.request.ProductUpdateRequest;
import com.heng.microservice.response.ApiResponse;
import com.heng.microservice.service.product.IProductService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/{api.prefix}/products")
public class ProductController {

    private final IProductService productService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts(){
        try {
            List<Product> products = productService.getAllProducts();
            return ResponseEntity.ok(new ApiResponse("Found", products));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Failed",e.getMessage()));
        }
    }

    @GetMapping("/product/{productId}/product")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId){
        try {
            Product product = productService.getProductById(productId);
            return ResponseEntity.ok(new ApiResponse("Success", product));
        } catch (ResourceNotFoundException e) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));

        }
    }


    @PostMapping("/add")
   public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest request) {
        try {
            Product theProduct = productService.addProduct(request);
            return ResponseEntity.ok(new ApiResponse("Success", theProduct));
        } catch (AlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
   }


   @PutMapping("/product/{productId}/update")
    public ResponseEntity<ApiResponse> updateProduct(
            @RequestBody ProductUpdateRequest request,
            @PathVariable Long productId) {
        try {
            Product theProduct = productService.updateProduct(request,productId);

            if(theProduct != null){
                productService.updateProduct(request,productId);
            }
            return ResponseEntity.ok(new ApiResponse("Update Product Success!",theProduct));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/product/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProduct( @PathVariable Long productId) {

        try {
           productService.deleteProductById(productId);
           return ResponseEntity.ok(new ApiResponse("Delete Product Success", productId));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

   @GetMapping("/products/by/brand-and-name")
    public ResponseEntity<ApiResponse> findProductsByBrandAndName(
            @RequestBody  String brandName,
            @PathVariable String productName
    ) {
        try {
            List<Product>  products = productService.getProductsByBrandAndName(brandName, productName);
            if(products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("No Product Found" , null));
            }
            return ResponseEntity.ok(new ApiResponse("Success", products));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }



    @GetMapping("/products/by/category-and-name")
    public ResponseEntity<ApiResponse> findProductsByCategoryAndName(
            @RequestBody  String categoryName,
            @PathVariable String productName
    ) {
        try {
            List<Product>  products = productService.getProductsByBrandAndName(categoryName, productName);
            if(products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("No Product Found" , null));
            }
            return ResponseEntity.ok(new ApiResponse("Success", products));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }


    @GetMapping("/products/{brandName}/products")
    public ResponseEntity<ApiResponse> findProductsByBrand(@PathVariable String brandName) {
        try {
            List<Product>  products = productService.getProductByBrand(brandName);
            if(products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("No Product Found" , null));
            }
            return ResponseEntity.ok(new ApiResponse("Success", products));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/products/{name}/products")
    public ResponseEntity<ApiResponse> findProductsByName(@PathVariable String name) {
        try {
            List<Product>  products = productService.getProductsByName(name);
            if(products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("No Product Found" , null));
            }
            return ResponseEntity.ok(new ApiResponse("Success", products));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/product/{category}/all/products")
    public ResponseEntity<ApiResponse> findProductByCategory(@PathVariable String category) {
        try {
            List<Product>  products = productService.getProductByCategory(category);
            if(products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("No Product Found" , null));
            }
            return ResponseEntity.ok(new ApiResponse("Success", products));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

}

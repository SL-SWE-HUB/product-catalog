package com.example.product_catalog_backend.controller;

import com.example.product_catalog_backend.entity.Product;
import com.example.product_catalog_backend.service.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping()
    public List<Product> getAllProducts(){
        return this.productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Optional<Product> searchByIdResult = this.productService.getProductById(id);
        if(searchByIdResult.isEmpty()) {
            return ResponseEntity.notFound().build(); // Returns 404 not found
        }
        return ResponseEntity.ok(searchByIdResult.get());
    }

    @PostMapping("/newProduct")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        if(product == null){
            return ResponseEntity.badRequest().build();
        }
        Product saveResult = this.productService.createProduct(product);
        return ResponseEntity.ok(saveResult);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product){
        if(product == null)
            return ResponseEntity.notFound().build();
        Optional<Product> updateResult = this.productService.updateProduct(id, product);
        if(updateResult.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updateResult.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        this.productService.getProductById(id);
        return ResponseEntity.accepted().build();
    }
}

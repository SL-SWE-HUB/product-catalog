package com.example.product_catalog_backend.service;

import com.example.product_catalog_backend.entity.Product;
import com.example.product_catalog_backend.repository.ProductRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepositoryInterface productRepository;

    // Constructor(s)

    @Autowired
    public ProductService(ProductRepositoryInterface productRepository){
        this.productRepository = productRepository;
    }

    // Method(s) start

    public Product createProduct(Product product){
        return this.productRepository.save(product);
    }

    public Optional<Product> updateProduct(Long id, Product productDetails){
        Optional<Product> productSearchResult = this.productRepository.findById(id);
        if(productSearchResult.isEmpty() || productDetails == null){
            return productSearchResult;
        }
        Product optionalToObject = productSearchResult.get();
        optionalToObject.setName(productDetails.getName());
        optionalToObject.setDescription(productDetails.getDescription());
        optionalToObject.setPrice(productDetails.getPrice());
        return productSearchResult;
    }

    public void deleteProduct(Long id){
        this.productRepository.deleteById(id);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }

    public String formatPrice(BigDecimal price){ // For unit testing
        if(price == null){
            return "N/A";
        }
        return "$" + price.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }


}

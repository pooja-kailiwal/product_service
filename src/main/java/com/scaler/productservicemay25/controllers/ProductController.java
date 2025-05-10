package com.scaler.productservicemay25.controllers;

import com.scaler.productservicemay25.models.Product;
import com.scaler.productservicemay25.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/{id}")
    public Product getSingleProduct(@PathVariable("id") Long productId){
        //Should we call FakeStore API here ? No, we should make a call to the Service.
        return productService.getSingleProduct(productId);
    }
    @GetMapping("/")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
    @PostMapping("/")
    public  Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }
    @DeleteMapping("/id")
    public boolean deleteProduct(@PathVariable Long id) {
        return  productService.deleteProduct(id);
    }
    @PutMapping("/id")
    public Product updateProduct(@PathVariable("id") Long productId, @RequestBody Product product){
        return productService.updateProduct(productId,product);
    }
}


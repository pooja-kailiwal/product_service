package com.scaler.productservicemay25.services;

import com.scaler.productservicemay25.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getSingleProduct(Long id);
    Product createProduct(Product product);
    Product updateProduct(Long id,Product product);
    boolean deleteProduct(Long id);
}

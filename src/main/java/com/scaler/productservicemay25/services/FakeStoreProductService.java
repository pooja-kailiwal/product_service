package com.scaler.productservicemay25.services;

import com.scaler.productservicemay25.dtos.FakeStoreProductDto;
import com.scaler.productservicemay25.models.Category;
import com.scaler.productservicemay25.models.Product;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service
public class FakeStoreProductService implements ProductService {
    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(Long productId) {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreProductDto.class);

        FakeStoreProductDto fakeStoreProductDto = fakeStoreProductDtoResponseEntity.getBody();

        //Convert FakeStoreProductDto into Product Object.

        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }
    @Override
    public List<Product> getAllProducts() {
        ResponseEntity<List<FakeStoreProductDto>> response = restTemplate.exchange(
                "https://fakestoreapi.com/products/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FakeStoreProductDto>>() {}
        );

        List<FakeStoreProductDto> fakeStoreDtos = response.getBody();

        List<Product> products = new ArrayList<>();
        if (fakeStoreDtos != null) {
            for (FakeStoreProductDto dto : fakeStoreDtos) {
                products.add(convertFakeStoreProductDtoToProduct(dto));
            }
        }

        return products;
    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreProductDto createdDto = restTemplate.postForObject(
                "https://fakestoreapi.com/products/",
                product,
                FakeStoreProductDto.class
        );
        return convertFakeStoreProductDtoToProduct(createdDto);

    }
    @Override
    public Product updateProduct(Long productId, Product product) {
        restTemplate.put("https://fakestoreapi.com/products/" + productId, product);
        return getSingleProduct(productId); // fetch updated product
    }
    @Override
    public boolean deleteProduct(Long productId) {
        try {
            restTemplate.delete("https://fakestoreapi.com/products/" + productId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    private static Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        if (fakeStoreProductDto == null) {
            return null;
        }

        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());

        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        return product;
    }
}

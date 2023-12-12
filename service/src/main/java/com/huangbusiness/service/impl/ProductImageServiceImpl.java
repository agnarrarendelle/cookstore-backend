package com.huangbusiness.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.huangbusiness.common.dto.ProductImageDto;
import com.huangbusiness.common.exception.ProductImageException;
import com.huangbusiness.common.vo.ProductImageVo;
import com.huangbusiness.repository.entity.Product;
import com.huangbusiness.repository.entity.ProductImage;
import com.huangbusiness.repository.repositories.ProductImageRepository;
import com.huangbusiness.service.ProductImageService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class ProductImageServiceImpl implements ProductImageService {
    @Autowired
    ProductImageRepository productImageRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Value("${product_image_api.url}")
    private String productImageApiUrl;

    @Value("${product_image_api.access_key}")
    private String productImageApiAccessKey;

    @Override
    public ProductImageVo getUploadUrl() {
        String imageKey = UUID.randomUUID().toString();
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", productImageApiAccessKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestBody = String.format("{\"imageKey\":\"%s\"}", imageKey);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        String requestUrl = String.format("%s/product-images", productImageApiUrl);

        try {
            // Make the HTTP POST request
            ResponseEntity<JsonNode> responseEntity = new RestTemplate()
                    .exchange(requestUrl, HttpMethod.POST, requestEntity, JsonNode.class);
            JsonNode map = responseEntity.getBody();
            return ProductImageVo.builder().uploadUrl(map.get("url").asText()).imageId(imageKey).build();
        } catch (Exception e) {
            throw new ProductImageException();
        }
    }

    @Override
    public ProductImageVo getImage(UUID id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", productImageApiAccessKey);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        String requestUrl = String.format("%s/product-image/%s", productImageApiUrl, id.toString());

        try {
            ResponseEntity<JsonNode> responseEntity = new RestTemplate()
                    .exchange(requestUrl, HttpMethod.GET, requestEntity, JsonNode.class);
            JsonNode map = responseEntity.getBody();
            return ProductImageVo.builder().imageUrl(map.get("url").asText()).build();
        } catch (Exception e) {
            throw new ProductImageException();
        }
    }

    @Override
    @Transactional
    public void saveImageDetails(ProductImageDto productImageDto) {
        ProductImage productImage = ProductImage
                .builder()
                .id(productImageDto.getId())
                .name(productImageDto.getName())
                .product(entityManager.getReference(Product.class, productImageDto.getProductId()))
                .build();

        productImageRepository.save(productImage);
    }
}

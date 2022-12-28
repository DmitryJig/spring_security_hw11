package com.spring.security.hw11.services;

import com.spring.security.hw11.dto.ProductDto;
import com.spring.security.hw11.exceptions.ResourceNotFoundException;
import com.spring.security.hw11.model.Product;
import com.spring.security.hw11.repositories.ProductRepository;
import com.spring.security.hw11.repositories.specifications.ProductSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Page<Product> findAll(Double minPrice, Double maxPrice, String partTitle, Integer page){
        Specification<Product> specification = Specification.where(null); // null значит спецификация ничего не проверяет
        if (minPrice != null){
            specification = specification.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null){
            specification = specification.and(ProductSpecifications.priceLessOrEqualsThan(maxPrice));
        }
        if (partTitle != null){
            specification = specification.and(ProductSpecifications.titleLike(partTitle));
        }
        return productRepository.findAll(specification, PageRequest.of(page - 1, 5));
    }

    public Optional<Product> findProductById(Long id){
        return productRepository.findById(id);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public void deleteProductById(Long id){
        productRepository.deleteById(id);
    }

    @Transactional
    public Product update(ProductDto productDto) {
        Product product = productRepository.findById(productDto.getId()).orElseThrow(()-> new ResourceNotFoundException("Невозможно обновить продукт, не найден в базе, id: " + productDto.getId()));
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle()); // так как метод транзакционный то продукт находится в контексте постоянства и все изменения внесутся в базу сами при выходе из метода
        return product;
    }
}

package com.spring.security.hw11.controllers;

import com.spring.security.hw11.converters.ProductConverter;
import com.spring.security.hw11.dto.ProductDto;
import com.spring.security.hw11.exceptions.ResourceNotFoundException;
import com.spring.security.hw11.model.Product;
import com.spring.security.hw11.services.ProductService;
import com.spring.security.hw11.services.UserService;
import com.spring.security.hw11.validators.ProductValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;
    private final ProductValidator productValidator;

    //http://localhost:8189/app/api/v1/products?p=1&min_price=1000&max_price=2000
    @GetMapping
    public Page<ProductDto> findAllProducts(
            @RequestParam(name = "pageIndex", defaultValue = "1") Integer page,
            @RequestParam(name = "min_price", required = false) Double minPrice,
            @RequestParam(name = "max_price", required = false) Double maxPrice,
            @RequestParam(name = "title_part", required = false) String titlePart
    ) {
        if (page < 1){
            page = 1;
        }
        return productService.findAll(minPrice, maxPrice, titlePart, page).map(productConverter::entityToDto);
    }

    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        Product product = productService.findProductById(id).orElseThrow(()->new ResourceNotFoundException("Product not found, id: " + id));
        return productConverter.entityToDto(product);
    }

    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED) // return status 201
    public ProductDto saveNewProduct(@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        productDto.setId(null);
        Product product = productConverter.dtoToEntity(productDto);
        product = productService.save(product);
        return productConverter.entityToDto(product);
    }

    // принимаем ДТО и ищем по его id продукт в базе данных, присваиваем ему имя и цену и снова сохраняем в БД
    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto){
        productValidator.validate(productDto);
        Product product = productService.update(productDto);
        return productConverter.entityToDto(product); // надо ли создавать новый объект? или вернуть входящий дто?
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }
}

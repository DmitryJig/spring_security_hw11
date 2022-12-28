package com.spring.security.hw11.validators;

import com.spring.security.hw11.dto.ProductDto;
import com.spring.security.hw11.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidator {
    List<String> errors = new ArrayList<>();
    public void validate(ProductDto productDto){
        if (productDto.getPrice() < 1){
            errors.add("Цена продукта не может быть меньше 1");
        }
        if (productDto.getTitle().isBlank()){
            errors.add("Продукт не может иметь пустое название");
        }
        if (!errors.isEmpty()){
            throw new ValidationException(errors);
        }
    }
}

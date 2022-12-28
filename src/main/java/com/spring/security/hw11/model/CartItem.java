package com.spring.security.hw11.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * класс покупка (строка корзины)
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartItem {
    private Product product;
    private Integer count;
}

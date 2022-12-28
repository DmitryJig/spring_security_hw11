package com.spring.security.hw11.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс корзина с товарами и количеством
 */
@Component
@Data
public class Cart {
    private List<CartItem> cartItems;

    @PostConstruct
    private void init(){
        this.cartItems = new ArrayList<>();
    }
}

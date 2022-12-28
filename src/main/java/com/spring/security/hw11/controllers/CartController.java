package com.spring.security.hw11.controllers;

import com.spring.security.hw11.model.CartItem;
import com.spring.security.hw11.repositories.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * контроллер для работы с корзиной
 */
@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Slf4j
public class CartController {
    private final CartRepository cartRepository;

    @GetMapping
    public List<CartItem> getCartList(){
        return cartRepository.getCartItems();
    }

    @GetMapping("/add/{id}")
    public void addProductToCart(@PathVariable Long id){
        cartRepository.addPtoductToCart(id, 1);
    }
}

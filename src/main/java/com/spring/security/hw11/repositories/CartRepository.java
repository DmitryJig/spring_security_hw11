package com.spring.security.hw11.repositories;

import com.spring.security.hw11.model.Cart;
import com.spring.security.hw11.model.CartItem;
import com.spring.security.hw11.model.Product;
import com.spring.security.hw11.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
@RequiredArgsConstructor
public class CartRepository {
    private final Cart cart;
    private final ProductService productService;

    public List<CartItem> getCartItems() {
        return cart.getCartItems();
    }

    // находим товар в базе по id, проверяем есть ли он в корзине,
    // если есть то меняем его количество, если нет то добавляем в корзину
    public void addPtoductToCart(Long id, Integer count) {
        Product product = productService.findProductById(id).get();
        AtomicBoolean productContains = new AtomicBoolean(false);
        cart.getCartItems().forEach(cartItem -> {
            if (cartItem.getProduct().equals(product)) {
                productContains.set(true);
                cartItem.setCount(cartItem.getCount() + count);
            }
        });
        if (!productContains.get()){
            cart.getCartItems().add(new CartItem(product, count));
        }
    }
}

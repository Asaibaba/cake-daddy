// Cart service
package com.cakedaddy.service;

import com.cakedaddy.model.CartItem;
import com.cakedaddy.model.Product;
import com.cakedaddy.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CartService {
    private final ProductRepository productRepo;

    public CartService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public void addToCart(Map<Long, CartItem> cart, Long productId) {
        Product product = productRepo.findById(productId).orElseThrow();
        CartItem item = cart.getOrDefault(productId,
                new CartItem(productId, product.getName(), product.getPrice(), 0));
        item.setQuantity(item.getQuantity() + 1);
        cart.put(productId, item);
    }

    public void removeFromCart(Map<Long, CartItem> cart, Long productId) {
        cart.remove(productId);
    }

    public double calculateTotal(Map<Long, CartItem> cart) {
        return cart.values().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }
}

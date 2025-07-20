// Order service
package com.cakedaddy.service;

import com.cakedaddy.model.*;
import com.cakedaddy.repository.OrderRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired private OrderRepository orderRepo;

    public Order placeOrder(String username, Map<Long, CartItem> cart) {
        Order order = new Order();
        order.setUsername(username);
        order.setCreatedAt(LocalDateTime.now());
        order.setTotal(cart.values().stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum());

        List<OrderItem> items = cart.values().stream().map(i -> {
            OrderItem item = new OrderItem();
            item.setProductId(i.getProductId());
            item.setName(i.getName());
            item.setQuantity(i.getQuantity());
            item.setPrice(i.getPrice());
            item.setOrder(order);
            return item;
        }).collect(Collectors.toList());

        order.setItems(items);
        return orderRepo.save(order);
    }

    public List<Order> getUserOrders(String username) {
        return orderRepo.findByUsername(username);
    }
}

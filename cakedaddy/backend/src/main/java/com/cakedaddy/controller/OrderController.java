// OrderController code here
package com.cakedaddy.controller;

import com.cakedaddy.model.*;
import com.cakedaddy.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired private OrderService orderService;

    @PostMapping("/place")
    public String placeOrder(HttpSession session, Authentication auth, Model model) {
        Map<Long, CartItem> cart = (Map<Long, CartItem>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            model.addAttribute("message", "Cart is empty!");
            return "cart";
        }

        Order order = orderService.placeOrder(auth.getName(), cart);
        session.removeAttribute("cart");

        model.addAttribute("order", order);
        return "order-success";
    }

    @GetMapping("/history")
    public String orderHistory(Authentication auth, Model model) {
        model.addAttribute("orders", orderService.getUserOrders(auth.getName()));
        return "orders";
    }
}

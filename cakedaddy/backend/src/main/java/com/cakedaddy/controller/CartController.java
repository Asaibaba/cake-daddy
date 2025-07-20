// CartController code here
package com.cakedaddy.controller;

import com.cakedaddy.model.CartItem;
import com.cakedaddy.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired private CartService cartService;

    @GetMapping
    public String viewCart(HttpSession session, Model model) {
        Map<Long, CartItem> cart = getCart(session);
        model.addAttribute("cartItems", cart.values());
        model.addAttribute("total", cartService.calculateTotal(cart));
        return "cart";
    }

    @PostMapping("/add/{productId}")
    public String addToCart(@PathVariable Long productId, HttpSession session) {
        Map<Long, CartItem> cart = getCart(session);
        cartService.addToCart(cart, productId);
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

    @PostMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable Long productId, HttpSession session) {
        Map<Long, CartItem> cart = getCart(session);
        cartService.removeFromCart(cart, productId);
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

    @SuppressWarnings("unchecked")
    private Map<Long, CartItem> getCart(HttpSession session) {
        Object cartObj = session.getAttribute("cart");
        if (cartObj == null) {
            session.setAttribute("cart", new HashMap<Long, CartItem>());
        }
        return (Map<Long, CartItem>) session.getAttribute("cart");
    }
}

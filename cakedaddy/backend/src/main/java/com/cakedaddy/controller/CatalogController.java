// CatalogController code here
package com.cakedaddy.controller;

import com.cakedaddy.model.Product;
import com.cakedaddy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CatalogController {

    @Autowired private ProductService service;

    @GetMapping("/catalog")
    public String showCatalog(@RequestParam(value = "category", required = false) String category, Model model) {
        List<Product> products = (category == null || category.isEmpty())
                ? service.getAllProducts()
                : service.getByCategory(category);
        model.addAttribute("products", products);
        return "catalog";
    }
}

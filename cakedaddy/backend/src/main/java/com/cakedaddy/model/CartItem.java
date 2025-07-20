// Cart item model
package com.cakedaddy.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Long productId;
    private String name;
    private double price;
    private int quantity;
}

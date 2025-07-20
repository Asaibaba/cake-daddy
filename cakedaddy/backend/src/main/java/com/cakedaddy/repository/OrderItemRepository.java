// Order item repository
package com.cakedaddy.repository;

import com.cakedaddy.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}

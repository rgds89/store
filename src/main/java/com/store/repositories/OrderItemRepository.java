package com.store.repositories;

import com.store.entities.OrderItem;
import com.store.entities.pk.OrderItemPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPk> {
}

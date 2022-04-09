package com.montealegreluis.ticketbeast.adapters.jpa.repositories.orders;

import com.montealegreluis.ticketbeast.orders.Order;
import com.montealegreluis.ticketbeast.shared.Uuid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersJpaRepository extends JpaRepository<Order, Uuid> {}

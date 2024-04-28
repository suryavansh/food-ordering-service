package com.bits.orderingservice.domain.repository;

import com.bits.orderingservice.domain.models.OrderId;
import com.bits.orderingservice.domain.models.Orders;
import com.bits.orderingservice.infrastructure.jpa.IJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends IJpaRepository<Orders, OrderId> {
}

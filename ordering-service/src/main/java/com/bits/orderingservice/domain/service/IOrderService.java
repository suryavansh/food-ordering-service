package com.bits.orderingservice.domain.service;

import com.bits.orderingservice.application.request.CreateOrderRequest;
import com.bits.orderingservice.application.response.OrderResponse;
import org.springframework.transaction.annotation.Transactional;

public interface IOrderService {
    @Transactional
    OrderResponse createOrder(CreateOrderRequest request) throws Exception;
}

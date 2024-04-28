package com.bits.orderingservice.application.response;

import com.bits.orderingservice.domain.models.Orders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private int status;
    private String id;
    private Set<OrderItemResponse> items;
    private OffsetDateTime createdDate;
    private OffsetDateTime modifiedDate;
    private BigDecimal totalAmount;

    public OrderResponse(Orders order) {
        items = new HashSet<>();
        this.id = order.getId().toString();
        this.status = order.getStatus().getValue();
        order.getOrderItems().forEach(orderItem -> items.add(new OrderItemResponse(orderItem)));
        this.createdDate = order.getCreatedDate();
        this.modifiedDate = order.getModifiedDate();
    }

}

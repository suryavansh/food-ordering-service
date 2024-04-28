package com.bits.orderingservice.domain.domainevents;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.bits.orderingservice.domain.models.OrderId;
import com.bits.orderingservice.domain.models.OrderItem;
import com.bits.orderingservice.domain.DomainEvent;
import com.bits.orderingservice.domain.DomainObjectId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class OrderCreated<T extends DomainObjectId> extends DomainEvent<T> {
    private String customerId;
    private Set<OrderItem> orderItems;
    private OffsetDateTime createdDate;

    public OrderCreated(T orderId, String customerId, Set<OrderItem> orderItems, OffsetDateTime createdDate) {
        this.setEntityId(orderId);
        this.setCustomerId( customerId);
        this.setOrderItems(orderItems);
        this.setCreatedDate(createdDate);
        this.setEventType(EventType.CREATED);
    }
}
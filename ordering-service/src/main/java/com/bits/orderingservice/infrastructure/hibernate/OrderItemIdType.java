package com.bits.orderingservice.infrastructure.hibernate;


import com.bits.orderingservice.domain.models.OrderItemId;
import com.bits.orderingservice.infrastructure.hibernate.DomainObjectIdCustomType;
import com.bits.orderingservice.infrastructure.hibernate.DomainObjectIdTypeDescriptor;

public class OrderItemIdType extends DomainObjectIdCustomType<OrderItemId> {
    private static final DomainObjectIdTypeDescriptor<OrderItemId> TYPE_DESCRIPTOR = new DomainObjectIdTypeDescriptor<>(
            OrderItemId.class, OrderItemId::new);

    public OrderItemIdType() {
        super(TYPE_DESCRIPTOR);
    }
}

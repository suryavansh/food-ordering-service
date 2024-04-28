package com.bits.orderingservice.infrastructure.hibernate;

import com.bits.orderingservice.domain.models.OrderId;
import com.bits.orderingservice.infrastructure.hibernate.DomainObjectIdCustomType;
import com.bits.orderingservice.infrastructure.hibernate.DomainObjectIdTypeDescriptor;

public class OrderIdType extends DomainObjectIdCustomType<OrderId> {
    private static final DomainObjectIdTypeDescriptor<OrderId> TYPE_DESCRIPTOR=
            new DomainObjectIdTypeDescriptor<>(OrderId.class,OrderId::new);

    public OrderIdType() {
        super(TYPE_DESCRIPTOR);
    }
}

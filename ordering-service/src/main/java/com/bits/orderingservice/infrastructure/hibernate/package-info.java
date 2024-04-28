@TypeDefs({
        @TypeDef(defaultForType = OrderId.class,typeClass = OrderIdType.class),
        @TypeDef(defaultForType = OrderItemId.class, typeClass = OrderItemIdType.class)
})
package com.bits.orderingservice.infrastructure.hibernate;

import com.bits.orderingservice.domain.models.OrderId;
import com.bits.orderingservice.domain.models.OrderItemId;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
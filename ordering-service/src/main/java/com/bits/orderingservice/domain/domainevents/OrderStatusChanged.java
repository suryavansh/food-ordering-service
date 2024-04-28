package com.bits.orderingservice.domain.domainevents;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.bits.orderingservice.domain.models.OrderId;
import com.bits.orderingservice.domain.models.OrderStatus;
import com.bits.orderingservice.domain.DomainEvent;
import com.bits.orderingservice.domain.DomainObjectId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class OrderStatusChanged<T extends DomainObjectId> extends DomainEvent<T> {

    private OffsetDateTime modifiedDate;
    private OrderStatus lastStatus;
    private OrderStatus curStatus;

    public OrderStatusChanged(T id, OrderStatus lastStatus, OrderStatus curStatus, OffsetDateTime modifiedDate) {
        this.setEntityId(id);
        this.setLastStatus(lastStatus);
        this.setCurStatus(curStatus);
        this.setModifiedDate(modifiedDate);
        this.setEventType(EventType.STATUS_CHANGED);
    }
}

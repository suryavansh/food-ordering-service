package com.bits.orderingservice.domain.domainevents;

import com.bits.orderingservice.domain.DomainEvent;
import com.bits.orderingservice.domain.DomainObjectId;

import java.util.List;

public interface IDomainEventPublisher {

    void publish(List<DomainEvent<? extends DomainObjectId>> eventList);
}

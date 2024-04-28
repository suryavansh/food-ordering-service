package com.bits.orderingservice.domain.models;

import com.bits.orderingservice.domain.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class OrderStatusChange implements ValueObject {

    private OffsetDateTime changedOn;
    @Enumerated(EnumType.ORDINAL)
    private OrderStatus status;
}

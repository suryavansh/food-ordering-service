package com.bits.orderingservice.domain.exceptions;

import com.bits.orderingservice.domain.policies.OrderErrorCode;
import com.bits.orderingservice.domain.DomainException;

public class OrderIdIsNullException extends DomainException {
    public OrderIdIsNullException(String source, OrderErrorCode errorCode, String errorMessage) {
        super(source, errorCode, errorMessage);
    }
}

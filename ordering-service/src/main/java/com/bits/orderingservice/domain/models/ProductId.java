package com.bits.orderingservice.domain.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.bits.orderingservice.domain.DomainObjectId;

public class ProductId extends DomainObjectId {
    @JsonCreator
    public ProductId(String uuid) {
        super(uuid);
    }
}
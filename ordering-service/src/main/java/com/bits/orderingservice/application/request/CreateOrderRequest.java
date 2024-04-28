package com.bits.orderingservice.application.request;

import com.bits.orderingservice.application.response.OrderItemResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {
    @NotNull
    private String customerId;
    @NotNull
    private Set<OrderItemResponse> items;

}

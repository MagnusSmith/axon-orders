package com.example.api.order;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 10/02/14
 * Time: 11:31
 *
 */
public class CreateOrderCommand {
    @TargetAggregateIdentifier
    private final OrderId orderId;

    private final String productId;

    public CreateOrderCommand(OrderId orderId, String productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    public OrderId getOrderId() {
        return orderId;
    }


    public String getProductId() {
        return productId;
    }

}

package com.example.cqrs.api.order;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 10/02/14
 * Time: 11:41
 */
public class CancelOrderCommand  {
    @TargetAggregateIdentifier
    private final OrderId orderId;

    public CancelOrderCommand(OrderId orderId) {
        this.orderId = orderId;
    }


    public OrderId getOrderId() {
        return orderId;
    }
}

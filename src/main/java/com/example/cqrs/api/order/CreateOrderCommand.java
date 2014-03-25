package com.example.cqrs.api.order;

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

    private final OrderDetails details;

    public CreateOrderCommand(OrderDetails details) {
        this.orderId = details.getOrderId();
        this.details = details;
    }

    public OrderId getOrderId() {
        return orderId;
    }


    public OrderDetails getDetails() {
        return details;
    }

}

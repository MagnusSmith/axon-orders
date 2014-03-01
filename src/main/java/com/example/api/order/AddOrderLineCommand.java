package com.example.api.order;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 19/02/14
 * Time: 16:25
 *
 */
public class AddOrderLineCommand {

    @TargetAggregateIdentifier
    private final OrderId orderId;
    private final OrderLineEntry details;


    public AddOrderLineCommand(OrderId orderId, OrderLineEntry details) {
        this.details = details;
        this.orderId = orderId;

    }


    public OrderId getOrderId() {
        return orderId;
    }

    public OrderLineEntry getDetails() {
        return details;
    }
}

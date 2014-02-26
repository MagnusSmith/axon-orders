package com.example.api.order;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 24/02/14
 * Time: 09:56
 * Copyright Advanced Computer Software Group 2014
 */
public class RemoveOrderLineCommand {

    @TargetAggregateIdentifier
    private final OrderId orderId;

    private final OrderLineId orderLineId;

    public RemoveOrderLineCommand(OrderId orderId, OrderLineId orderLineId){
        this.orderId = orderId;
        this.orderLineId = orderLineId;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public OrderLineId getOrderLineId() {
        return orderLineId;
    }
}

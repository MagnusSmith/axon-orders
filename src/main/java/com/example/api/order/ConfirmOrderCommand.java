package com.example.api.order;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 10/02/14
 * Time: 11:41
 * Copyright Advanced Computer Software Group 2014
 */
public class ConfirmOrderCommand {
    @TargetAggregateIdentifier
    private final OrderId orderId;

    public ConfirmOrderCommand(OrderId orderId) {
        this.orderId = orderId;
    }
}

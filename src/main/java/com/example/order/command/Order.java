package com.example.order.command;

import com.example.component.Loggable;
import com.example.api.order.OrderCreatedEvent;
import com.example.api.order.OrderId;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.slf4j.Logger;

/**
* Created with IntelliJ IDEA.
* User: magnus.smith
* Date: 10/02/14
* Time: 13:55
*
*/
public class Order extends AbstractAnnotatedAggregateRoot {

    @Loggable
    private Logger log;

    @AggregateIdentifier
    private OrderId orderId;

     Order(){}

    public Order(OrderId identifier, String productId){
        apply(new OrderCreatedEvent(identifier, productId));
    }


    @EventSourcingHandler
    public void on(OrderCreatedEvent event) {
        this.orderId = event.getOrderId();
    }

    @Override
    public OrderId getIdentifier() {
        return orderId;
    }

}

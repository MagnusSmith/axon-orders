package com.example.order.command;

import com.example.api.order.OrderLineAddedEvent;
import com.example.api.order.OrderLineId;
import com.example.api.product.ProductId;
import com.example.component.Loggable;
import com.example.api.order.OrderCreatedEvent;
import com.example.api.order.OrderId;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcedMember;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.slf4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
* Created with IntelliJ IDEA.
* User: magnus.smith
* Date: 10/02/14
* Time: 13:55
*
*/
public class Order extends AbstractAnnotatedAggregateRoot {

    private static final long serialVersionUID = 6778782949492587631L;

    @Loggable
    private Logger log;

    @AggregateIdentifier
    private OrderId orderId;

    @EventSourcedMember
    private List<OrderLine> lines = new ArrayList<>();

    @SuppressWarnings("UnusedDeclaration")
    Order(){}

    public Order(OrderId identifier, String productId){
        apply(new OrderCreatedEvent(identifier, productId));
    }


    public void addLine(OrderLineId orderLineId, ProductId productId, String description, BigDecimal price, int quantity){
        lines.add(new OrderLine(orderLineId, productId, description, price, quantity));
        apply(new OrderLineAddedEvent(orderLineId, productId, orderId, description, price, quantity));
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

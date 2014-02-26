package com.example.order.command;

import com.example.api.order.OrderLineAddedEvent;
import com.example.api.order.OrderLineId;
import com.example.api.product.ProductId;
import com.example.component.Loggable;
import com.example.api.order.OrderCreatedEvent;
import com.example.api.order.OrderId;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
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


    public void addLine(ProductId productId, String description, BigDecimal price, int quantity){
        OrderLine line = new OrderLine(productId, description, price, quantity);
        lines.add(line);
        apply(new OrderLineAddedEvent(line.getOrderLineId(), productId, orderId, description, price, quantity));
    }

    public void removeLine(final OrderLineId orderLineId){
        final boolean found = Iterables.removeIf(lines, new Predicate<OrderLine>() {
            @Override
            public boolean apply(OrderLine line) {
                return orderLineId.equals(line.getOrderLineId());
            }
        });
        if(found){
           log.info("removed orderLine[{}] from order", orderLineId);

        }
    }


    @EventSourcingHandler
    public void on(OrderCreatedEvent event) {
        this.orderId = event.getOrderId();
    }

    @Override
    public OrderId getIdentifier() {
        return orderId;
    }


    private List<OrderLine> filterLines(Predicate<OrderLine> predicate){
        Iterable<OrderLine> filtered = FluentIterable.from(lines).filter(predicate);
        return Lists.newArrayList(filtered);
    }
}

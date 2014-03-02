package com.example.order.command;

import com.example.api.order.*;
import com.example.component.Loggable;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcedMember;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Date;
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

    private Date dateTimeOfSubmission;

    @EventSourcedMember
    private List<OrderLine> lines = new ArrayList<>();

    @SuppressWarnings("UnusedDeclaration")
    Order(){}

    public Order(OrderDetails orderDetails){
        apply(new OrderCreatedEvent(orderDetails));
    }


//    public void addLine(OrderLineDetails details){
//        OrderLine line = new OrderLine(details);
//        lines.add(line);
//        apply(new OrderLineAddedEvent(line.getOrderLineId(), orderId, details));
//    }
//
//    public void removeLine(final OrderLineId orderLineId){
//        final boolean found = Iterables.removeIf(lines, new Predicate<OrderLine>() {
//            @Override
//            public boolean apply(OrderLine line) {
//                return orderLineId.equals(line.getOrderLineId());
//            }
//        });
//        if(found){
//           log.info("removed orderLine[{}] from order", orderLineId);
//
//        }
//    }
//

    @EventSourcingHandler
    public void on(OrderCreatedEvent event) {
        OrderDetails details = event.getOrderDetails();
        orderId = details.getOrderId();
        dateTimeOfSubmission = details.getDateTimeOfSubmission();
        lines = FluentIterable.from(details.getLines()).transform(new Function<OrderLineDetails, OrderLine>() {
            @Override
            public OrderLine apply(OrderLineDetails details) {
                return new OrderLine(details);
            }
        }).toList();


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

package com.example.web.ui.order;

import com.example.cqrs.api.order.CancelOrderCommand;
import com.example.cqrs.api.order.CreateOrderCommand;
import com.example.cqrs.api.order.OrderDetails;
import com.example.cqrs.api.order.OrderId;
import com.example.common.logging.Loggable;
import com.example.cqrs.concrete.order.query.OrderQueryRepository;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.repository.AggregateNotFoundException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 20/03/2014
 * Time: 15:39
 * Copyright Advanced Computer Software Group 2014
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Loggable
    Logger log;

    @Autowired
    CommandGateway gateway;

    @Autowired
    private OrderQueryRepository orderQuery;



    @Override
    public Order createOrder(Order order) {

        CreateOrderCommand orderCommand = new CreateOrderCommand(order.toOrderDetails());
        OrderId orderId = gateway.sendAndWait(orderCommand);

        return Order.fromOrderDetails(orderQuery.findOne(orderId));
    }


    @Override
    public void cancelOrder(String id) {
        OrderId toDelete = new OrderId(id);

        CancelOrderCommand cancelCommand = new CancelOrderCommand(toDelete);

        try {
            gateway.sendAndWait(cancelCommand);
        } catch (AggregateNotFoundException nfe) {
            log.warn("could not delete {}", id, nfe.getLocalizedMessage());
            throw new OrderNotFoundException(String.format("Order[id= %s] Not Found!", id));

        }



    }


    @Override
    public Order find(String id) {
        return Order.fromOrderDetails(orderQuery.findOne(new OrderId(id)));
    }



    @Override
    public List<Order> findAll() {
        return FluentIterable
                .from(orderQuery.findAll())
                .transform(new Function<OrderDetails, Order>() {
                    public Order apply(OrderDetails details) {
                        return Order.fromOrderDetails(details);
                    }
                }).toList();

    }
}

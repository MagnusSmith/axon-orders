package com.example.web.rest;


import com.example.api.order.CancelOrderCommand;
import com.example.api.order.CreateOrderCommand;
import com.example.api.order.OrderDetails;
import com.example.api.order.OrderId;
import com.example.component.Loggable;
import com.example.order.query.OrderQueryRepository;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.repository.AggregateNotFoundException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("/rest/orders")
public class OrderController {

    @Loggable
    Logger log;

    @Autowired
    CommandGateway gateway;

    @Autowired
    private OrderQueryRepository orderQuery;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Order> createOrder(@RequestBody Order order, UriComponentsBuilder builder) {

        CreateOrderCommand orderCommand = new CreateOrderCommand(order.toOrderDetails());
        OrderId orderId = gateway.sendAndWait(orderCommand);

        Order newOrder = Order.fromOrderDetails(orderQuery.findOne(orderId));

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/rest/orders/{id}")
                        .buildAndExpand(orderId.toString()).toUri());

        return new ResponseEntity<>(newOrder, headers, HttpStatus.CREATED);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<OrderId> cancelOrder(@PathVariable String id) {
        OrderId toDelete = new OrderId(id);

        CancelOrderCommand cancelCommand = new CancelOrderCommand(toDelete);

        try {
            gateway.sendAndWait(cancelCommand);
        } catch (AggregateNotFoundException nfe) {
            log.warn("could not delete {}", id, nfe.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(toDelete, HttpStatus.OK);

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Order find(@PathVariable String id) {
        return Order.fromOrderDetails(orderQuery.findOne(new OrderId(id)));
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<Order> findAll() {
        return FluentIterable
                .from(orderQuery.findAll())
                .transform(new Function<OrderDetails, Order>() {
                    public Order apply(OrderDetails details) {
                        return Order.fromOrderDetails(details);
                    }
                }).toList();

    }


    private CommandCallback<Object> newCallBackHandler() {
        return new CommandCallback<Object>() {
            @Override
            public void onSuccess(Object o) {
                System.out.println("Command Success! :-)");
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("Command Fail! :-(" + throwable.getMessage());
            }
        };
    }

}

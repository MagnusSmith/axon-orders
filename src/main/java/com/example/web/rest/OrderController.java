package com.example.web.rest;


import com.example.api.order.CreateOrderCommand;
import com.example.api.order.OrderId;
import com.example.order.query.OrderQueryRepository;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/rest/orders")
public class OrderController {

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


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Order find(@PathVariable String id) {
        return Order.fromOrderDetails(orderQuery.findOne(new OrderId(id)));
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

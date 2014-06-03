package com.example.web.ui.order;


import com.example.common.logging.Logger;
import org.axonframework.commandhandling.CommandCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("/rest/orders")
public class OrderRestController {

    @Logger
    org.slf4j.Logger log;

    @Autowired
    OrderService orderService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Order> createOrder(@RequestBody Order order, UriComponentsBuilder builder) {

        Order created = orderService.createOrder(order);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/rest/orders/{id}")
                        .buildAndExpand(created.getIdentifier()).toUri());

        return new ResponseEntity<>(created, headers, HttpStatus.CREATED);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<String> cancelOrder(@PathVariable String id) {

        try {
            orderService.cancelOrder(id);
        } catch (OrderNotFoundException onf) {
            log.warn("could not delete {}", id, onf.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(id, HttpStatus.OK);

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Order find(@PathVariable String id) {
        return orderService.find(id);
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<Order> findAll() {
        return orderService.findAll();

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

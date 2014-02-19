package com.example.order.rest;

import com.example.api.order.AddOrderLineCommand;
import com.example.api.order.CreateOrderCommand;
import com.example.api.order.OrderId;
import com.example.api.order.OrderLineId;
import com.example.api.product.ProductId;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.math.BigDecimal;
import java.util.UUID;


@RestController
public class OrderController {

    @Autowired
    CommandGateway gateway;
    
    @RequestMapping("/")
    public String index() {
        OrderId orderId = new OrderId();

        gateway.send((new CreateOrderCommand(orderId, "Product One")), new CommandCallback<Object>() {
            @Override
            public void onSuccess(Object o) {
                System.out.println("Create Order Success!");
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("Create Order Fail! " + throwable.getMessage());
            }
        });

        ProductId productId = new ProductId();
        OrderLineId lineId = new OrderLineId();
        gateway.send((new AddOrderLineCommand(lineId, productId, orderId, "Widget", BigDecimal.ONE, 10)), new CommandCallback<Object>() {
            @Override
            public void onSuccess(Object o) {
                System.out.println("Add Order Line Success!");
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("Add Order Line Fail! " + throwable.getMessage());
            }
        });
        // and let's send some Commands on the CommandBus.

        return "Greetings from Spring Boot3!";
    }
    
}

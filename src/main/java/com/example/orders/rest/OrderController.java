package com.example.orders.rest;

import com.example.orders.api.CreateOrderCommand;
import com.example.orders.api.OrderId;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import java.util.UUID;


@RestController
public class OrderController {

    @Autowired
    CommandGateway gateway;
    
    @RequestMapping("/")
    public String index() {
        final String itemId1 = UUID.randomUUID().toString();
        gateway.send((new CreateOrderCommand(new OrderId(itemId1), "Product One")), new CommandCallback<Object>() {
            @Override
            public void onSuccess(Object o) {
                System.out.println("Success!");
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("Fail! " + throwable.getMessage());
            }
        });

        // and let's send some Commands on the CommandBus.

        return "Greetings from Spring Boot3!";
    }
    
}

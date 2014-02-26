package com.example.order.rest;

import com.example.api.order.AddOrderLineCommand;
import com.example.api.order.CreateOrderCommand;
import com.example.api.order.OrderId;
import com.example.api.product.ProductId;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.math.BigDecimal;


@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    CommandGateway gateway;


    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public OrderId createOrder() {
        CreateOrderCommand  createOrder = new CreateOrderCommand("Product One");
        gateway.send(createOrder, newCallBackHandler());
        return createOrder.getOrderId();
    }


    @RequestMapping(value = "/{orderId}/addLine", method = RequestMethod.GET)
    public void addOrderLine(@PathVariable OrderId orderId){
        ProductId productId = new ProductId();
        AddOrderLineCommand addLineCommand =  new AddOrderLineCommand(productId, orderId, "Widget", BigDecimal.ONE, 10);
        gateway.send(addLineCommand, newCallBackHandler());
    }

    private CommandCallback<Object> newCallBackHandler()  {
         return  new CommandCallback<Object>() {
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

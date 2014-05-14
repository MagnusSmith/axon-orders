package com.example.cqrs.concrete.order.query;

import com.example.cqrs.api.order.OrderCancelledEvent;
import com.example.common.logging.Loggable;
import com.example.cqrs.api.order.OrderCreatedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 11/02/14
 * Time: 15:18
 *
 */
@Component
public class OrderListener {


    @Loggable
    private Logger log;

    @Autowired
    OrderEntryRepository repository;


    @EventHandler
    public void handleOrderCreatedEvent(OrderCreatedEvent event){
       log.info("handling OrderCreatedEvent");
        repository.saveAndFlush((OrderEntry)event.getOrderDetails());
    }

    @EventHandler
    public void handleOrderCancelledEvent(OrderCancelledEvent event){
         repository.delete(event.getOrderId());
    }
}

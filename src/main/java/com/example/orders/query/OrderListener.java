package com.example.orders.query;

import com.example.component.Loggable;
import com.example.orders.api.OrderCreatedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 11/02/14
 * Time: 15:18
 *
 */
@Component
public class OrderListener {


    @Loggable Logger log;

    @Autowired
    OrderEntryRepository repository;


    @EventHandler
    public void handleOrderCreatedEvent(OrderCreatedEvent event){
       log.info("handling OrderCreatedEvent");
        repository.saveAndFlush(new OrderEntity(event.getOrderId()));
    //    throw new RuntimeException("RuntimeException from OrderListener handleOrderCreatedEvent");
    }
}

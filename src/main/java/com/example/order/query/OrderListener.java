package com.example.order.query;

import com.example.component.Loggable;
import com.example.api.order.OrderCreatedEvent;
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
        OrderEntry entry = (OrderEntry)event.getOrderDetails();
        repository.saveAndFlush( entry );
       log.info("saved");
    }
}

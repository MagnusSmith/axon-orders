package com.example.product.query;

import com.example.api.product.ProductCreatedEvent;
import com.example.component.Loggable;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;

/**
 * Created by michael.klos on 19/02/14.
 */
@Component
public class ProductListener {

    @Loggable
    private Logger log;

    @Autowired
    ProductEntryRepository repository;

    @EventHandler
    public void handleProductCreatedEvent(ProductCreatedEvent event) {
        log.info("Handling ProductCreatedEvent");
        repository.saveAndFlush(new ProductEntry(event.getProductId(), event.getModelNumer(), event.getBrand()));
    }

}

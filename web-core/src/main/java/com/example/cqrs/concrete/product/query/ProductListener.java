package com.example.cqrs.concrete.product.query;

import com.example.common.logging.Logger;
import com.example.cqrs.api.product.ProductCreatedEvent;
import com.example.cqrs.api.product.ProductDeletedEvent;
import com.example.cqrs.api.product.ProductUpdatedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by michael.klos on 19/02/14.
 */
@Component
public class ProductListener {

    @Logger
    private org.slf4j.Logger log;

    @Autowired
    ProductEntryRepository repository;

    @EventHandler
    public void handleProductCreatedEvent(ProductCreatedEvent event) {
        log.info("Handling ProductCreatedEvent");
        repository.saveAndFlush((ProductEntry)event.getProductDetails());
    }

    @EventHandler
    public void handleProductDeletedEvent(ProductDeletedEvent event) {
        log.info("Handling ProductDeletedEvent");
        repository.delete(event.getProductId());
    }

    @EventHandler
    public void handleProductUpdatedEvent(ProductUpdatedEvent event) {
        log.info("Handling ProductUpdatedEvent");
        repository.saveAndFlush((ProductEntry)event.getProductDetails());
    }

}

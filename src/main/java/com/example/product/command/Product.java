package com.example.product.command;

import com.example.api.product.ProductCreatedEvent;
import com.example.api.product.ProductDeletedEvent;
import com.example.api.product.ProductId;
import com.example.component.Loggable;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import java.util.logging.Logger;

/**
 * Created by michael.klos on 19/02/14.
 */
public class Product extends AbstractAnnotatedAggregateRoot {

    @Loggable
    private Logger log;

    @AggregateIdentifier
    private ProductId productId;

    Product(){}

    public Product(ProductId identifier, String modelNumber, String brand){
        apply(new ProductCreatedEvent(identifier, modelNumber, brand));
    }

    public void deleteProduct(ProductId identifier) {
        apply(new ProductDeletedEvent(identifier));
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent event) {
        this.productId = event.getProductId();
    }

    //TODO not sure about this - should we handle all events in this way??
    @EventSourcingHandler
    public void on(ProductDeletedEvent event) {
        this.productId = event.getProductId();
    }

    @Override
    public ProductId getIdentifier() {
        return productId;
    }
}

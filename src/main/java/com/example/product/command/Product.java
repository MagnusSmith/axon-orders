package com.example.product.command;

import com.example.api.product.*;
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

    public Product(ProductDetails productDetails){
        apply(new ProductCreatedEvent(productDetails));
    }

    public void delete() {
        markDeleted();
        apply(new ProductDeletedEvent(productId));
    }

    public void update(ProductDetails productDetails) {
        apply(new ProductUpdatedEvent(productDetails));
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent event) {
        this.productId = event.getProductDetails().getId();
    }

    @Override
    public ProductId getIdentifier() {
        return productId;
    }
}

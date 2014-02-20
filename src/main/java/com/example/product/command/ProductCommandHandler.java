package com.example.product.command;

import com.example.component.Loggable;
import com.example.api.product.CreateProductCommand;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.repository.Repository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by michael.klos on 19/02/14.
 */
@Component
public class ProductCommandHandler {

    @Loggable
    private Logger log;

    @Autowired
    @Qualifier("productRepository")
    private Repository<Product> repository;


    @CommandHandler
    public void handle(final CreateProductCommand createProductCommand) {
        log.info("Received a command for a new Product {}", createProductCommand.getProductId());
        Product product = new Product(createProductCommand.getProductId(), createProductCommand.getModelNumber(), createProductCommand.getBrand());
        repository.add(product);
    }

}

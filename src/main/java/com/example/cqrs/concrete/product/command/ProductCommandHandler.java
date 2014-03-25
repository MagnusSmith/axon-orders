package com.example.cqrs.concrete.product.command;

import com.example.cqrs.api.product.DeleteProductCommand;
import com.example.cqrs.api.product.UpdateProductCommand;
import com.example.common.logging.Loggable;
import com.example.cqrs.api.product.CreateProductCommand;
import org.axonframework.commandhandling.annotation.CommandHandler;
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
        Product product = new Product(createProductCommand.getProductDetails());
        repository.add(product);
    }

    @CommandHandler
    public void handle(final DeleteProductCommand deleteProductCommand) {
        log.info("Received a command to delete a product {}" , deleteProductCommand.getProductId());
        Product product = repository.load(deleteProductCommand.getProductId());
        product.delete();
    }

    @CommandHandler
    public void handle(final UpdateProductCommand updateProductCommand) {
        log.info("Received a command to update a product {}", updateProductCommand.getProductId());
        Product product = repository.load(updateProductCommand.getProductId());
        product.update(updateProductCommand.getProductDetails());
    }
}

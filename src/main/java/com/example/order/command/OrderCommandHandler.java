package com.example.order.command;

import com.example.api.order.*;
import com.example.component.Loggable;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 10/02/14
 * Time: 13:30
 *
 */

@Component
public class OrderCommandHandler {

    @Loggable
    private Logger log;

    @Autowired
    @Qualifier("orderRepository")
    private Repository<Order> repository;




    @CommandHandler
    public void handle(final CreateOrderCommand command) {
        log.info("Received a command for a new OrderEntry with Product : {}", command.getProductId());
        Order order = new Order(command.getOrderId(), command.getProductId());
        repository.add(order);
    }


    /**
     * Confirms an OrderEntry
     * An {@code AggregateNotFoundException} is thrown if the identifier does not represent a valid OrderEntry.
     *
     * @param command
     */
    @CommandHandler
    public void handle(final ConfirmOrderCommand command) {
        log.debug("Received a command to confirm Order");
    }


    /**
     * Cancels an OrderEntry.
     * If the identifier does not exist, an {@code AggregateNotFoundException} is thrown.
     *
     * @param command
     */
    @CommandHandler
    public void handle(final CancelOrderCommand command) {
        log.debug("Received a command to cancel Order");
    }


    @CommandHandler
    public void handle(final AddOrderLineCommand command) {
        log.info("Received a command to Add OrderLine");
        Order order = repository.load(command.getOrderId());
        order.addLine(command.getProductId(), command.getDescription(), command.getPrice(), command.getQuantity());

    }


    @CommandHandler
    public void handle(final RemoveOrderLineCommand command) {
        log.info("Received a command to remove OrderLine") ;
        Order order = repository.load(command.getOrderId());
        order.removeLine(command.getOrderLineId());

    }

}

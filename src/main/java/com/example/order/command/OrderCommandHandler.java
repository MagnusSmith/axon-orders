package com.example.order.command;

import com.example.api.order.*;
import com.example.component.Loggable;
import com.example.order.query.OrderQueryRepository;
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
    public OrderId  handle(final CreateOrderCommand command) {
        log.info("Received a command for a new Order with id : {}", command.getDetails().getOrderId().toString());
        Order order = new Order(command.getDetails());
        repository.add(order);
        return order.getIdentifier();
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
        Order order = repository.load(command.getOrderId());
        order.cancel();



    }



}

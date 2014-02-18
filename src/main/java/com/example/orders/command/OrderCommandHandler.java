package com.example.orders.command;

import com.example.orders.api.CancelOrderCommand;
import com.example.orders.api.ConfirmOrderCommand;
import com.example.orders.api.CreateOrderCommand;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final static Logger logger = LoggerFactory.getLogger(OrderCommandHandler.class);

    @Autowired
    @Qualifier("orderRepository")
    private Repository<Order> repository;




    @CommandHandler
    public void handle(final CreateOrderCommand command) {
        logger.info("Received a command for a new Order with Product : {}", command.getProductId());
        Order order = new Order(command.getOrderId(), command.getProductId());
        repository.add(order);
    }


    /**
     * Confirms an Order
     * An {@code AggregateNotFoundException} is thrown if the identifier does not represent a valid Order.
     *
     * @param command
     */
    @CommandHandler
    public void handle(final ConfirmOrderCommand command) {
        logger.debug("Received a command to confirm Order");
    }


    /**
     * Cancels an Order.
     * If the identifier does not exist, an {@code AggregateNotFoundException} is thrown.
     *
     * @param command
     */
    @CommandHandler
    public void handle(final CancelOrderCommand command) {
        logger.debug("Received a command to cancel Order");
    }



//    public void setRepository(Repository<Order> orderRepository) {
//        this.repository = orderRepository;
//    }
}

package com.example.cqrs.api.order;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 28/02/14
 * Time: 16:10
 *
 */
public interface OrderDetails {

    OrderId getOrderId();

    Date getDateTimeOfSubmission();

    List<? extends OrderLineDetails> getLines();
}

package com.example.web.ui.order;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 13/05/2014
 * Time: 08:51
 * Copyright Advanced Computer Software Group 2014
 */
public interface OrderService {
    Order createOrder(Order order);

    void cancelOrder(String id);

    Order find(String id);

    List<Order> findAll();
}

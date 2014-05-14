package com.example.test.web;

import com.example.web.ui.order.OrderService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 13/05/2014
 * Time: 08:48
 */
@Configuration

public class OrderMvcControllerTestContextConfiguration {

    @Bean
    public OrderService orderService() {
        return Mockito.mock(OrderService.class);
    }
}

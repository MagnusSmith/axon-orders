package com.example.test.rest;

import com.example.web.rest.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

/**
 * Created by magnus on 02/03/14.
 */
public class RestDataFixture {

    public static Order standardOrder(){
        Order order = new Order();
        order.addLine("PROD_1", "Nuts and Bolts", new BigDecimal(9.99), 5);
        order.addLine("PROD_2", "Widgets", new BigDecimal(1.99), 50);
        order.submit();
        return order;
    }


    public static String standardOrderJson() {
        ObjectMapper mapper = new ObjectMapper();
        Order order = standardOrder();
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(order);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

}

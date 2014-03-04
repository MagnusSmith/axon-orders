package com.example.test.rest;



import com.example.web.rest.Order;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;


import org.junit.Test;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by magnus on 02/03/14.
 */
public class OrderTests {


     @Test
    public void thatOrdersCanBeAddedAndQueried() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));


        RestTemplate template = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

        HttpEntity<String> requestEntity = new HttpEntity<>(
                RestDataFixture.standardOrderJson(), headers);

         System.out.println(RestDataFixture.standardOrderJson());

         ResponseEntity<Order> entity = null;

        entity = template.postForEntity(
                "http://localhost:8080/axon-orders/rest/orders",
                requestEntity, Order.class);

        String path = entity.getHeaders().getLocation().getPath();

        assertEquals(HttpStatus.CREATED, entity.getStatusCode());
        assertTrue(path.startsWith("/axon-orders/rest/orders/"));
        Order order = entity.getBody();
        System.out.println("The Order ID is " + order.getIdentifier());

    }

}

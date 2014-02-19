package com.example.order.query;

import com.example.api.order.OrderId;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
* Created with IntelliJ IDEA.
* User: magnus.smith
* Date: 13/02/14
* Time: 17:07
*
*/
@Entity
public class OrderEntry {

    @Id
    private OrderId id;



    OrderEntry(){};

    OrderEntry(OrderId id){
        this.id = id;
    }
}

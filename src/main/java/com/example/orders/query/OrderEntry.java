package com.example.orders.query;

import com.example.orders.api.OrderId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

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

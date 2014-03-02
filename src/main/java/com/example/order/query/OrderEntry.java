package com.example.order.query;

import com.example.api.order.OrderDetails;
import com.example.api.order.OrderId;
import com.example.api.order.OrderLineDetails;
import com.example.web.rest.OrderLine;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* Created with IntelliJ IDEA.
* User: magnus.smith
* Date: 13/02/14
* Time: 17:07
*
*/
@Entity
public class OrderEntry implements OrderDetails {

    @Id
    private OrderId id;

    private Date dateTimeOfSubmission;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true, fetch = FetchType.EAGER)
    private List<OrderLineEntry> lines = new ArrayList<>();




    private OrderEntry(){};

    public OrderEntry(OrderId id, Date dateTimeOfSubmission, List<OrderLineEntry> lines){
        this.id = id;
        this.dateTimeOfSubmission = dateTimeOfSubmission;
        this.lines = lines;
    }

    @Override
    public OrderId getOrderId() {
        return id;
    }

    @Override
    public Date getDateTimeOfSubmission() {
        return dateTimeOfSubmission;
    }

    @Override
    public List<? extends OrderLineDetails> getLines() {
        return lines;
    }


}

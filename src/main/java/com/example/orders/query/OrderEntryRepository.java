package com.example.orders.query;


import com.example.orders.api.OrderId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
* Created with IntelliJ IDEA.
* User: magnus.smith
* Date: 13/02/14
* Time: 19:06
*
*/
@Repository
public interface OrderEntryRepository extends JpaRepository<OrderEntry, OrderId>{
}

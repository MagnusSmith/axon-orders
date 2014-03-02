package com.example.order.query;

import com.example.api.order.OrderId;
import com.example.config.ReadOnlyRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by magnus on 01/03/14.
 */
@Repository
public interface OrderQueryRepository extends ReadOnlyRepository<OrderEntry, OrderId> {
    long count();
}


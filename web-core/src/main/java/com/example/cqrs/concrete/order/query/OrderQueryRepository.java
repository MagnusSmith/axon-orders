package com.example.cqrs.concrete.order.query;

import com.example.cqrs.api.order.OrderId;
import com.example.common.repository.ReadOnlyRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by magnus on 01/03/14.
 */
@Repository
public interface OrderQueryRepository extends ReadOnlyRepository<OrderEntry, OrderId> {
    long count();
}


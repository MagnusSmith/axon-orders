package com.example.orders.api;

import org.axonframework.common.Assert;
import org.axonframework.domain.IdentifierFactory;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 10/02/14
 * Time: 12:09
 *
 */
public class OrderId implements Serializable {
    private static final long serialVersionUID = -7842002574176005113L;

    private String identifier;

    public OrderId() {
        this.identifier = IdentifierFactory.getInstance().generateIdentifier();
    }

    public OrderId(String identifier) {
        Assert.notNull(identifier, "Identifier may not be null");
        this.identifier = identifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderId that = (OrderId) o;

        return identifier.equals(that.identifier);

    }

    @Override
    public int hashCode() {
        return identifier.hashCode();
    }

    @Override
    public String toString() {
        return identifier;
    }
}

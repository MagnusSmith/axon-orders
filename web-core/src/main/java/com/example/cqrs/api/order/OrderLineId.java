package com.example.cqrs.api.order;

import org.axonframework.common.Assert;
import org.axonframework.domain.IdentifierFactory;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 19/02/14
 * Time: 14:40
 *
 */
public class OrderLineId implements Serializable {
    private static final long serialVersionUID = -7842002574176005113L;

    private String identifier;

    public OrderLineId() {
        this.identifier = IdentifierFactory.getInstance().generateIdentifier();
    }

    public OrderLineId(String identifier) {
        Assert.notNull(identifier, "Identifier may not be null");
        this.identifier = identifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderLineId that = (OrderLineId) o;

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

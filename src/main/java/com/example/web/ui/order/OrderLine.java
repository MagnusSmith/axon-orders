package com.example.web.ui.order;

import com.example.cqrs.api.order.OrderLineDetails;
import com.example.cqrs.api.order.OrderLineId;
import com.example.cqrs.api.product.ProductId;
import com.example.cqrs.concrete.order.query.OrderLineFactory;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 28/02/14
 * Time: 14:18
 */
public class OrderLine implements Serializable {
    @NotEmpty
    private String description;

   @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private BigDecimal price;

    @NotEmpty
    private String productIdentifier;

    @NotNull
    @Min(value = 1)
    private Integer quantity;

    private String identifier;


    public OrderLine() {
        super();
        identifier = (new OrderLineId()).toString();
    }

    ;

    public OrderLine(String productIdentifier, String description, BigDecimal price, int quantity) {
        this();
        this.description = description;
        this.price = price;
        this.productIdentifier = productIdentifier;
        this.quantity = quantity;

    }


    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getProductIdentifier() {
        return productIdentifier;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getIdentifier() { return identifier; }


    public void setDescription(final String description) {
        this.description = description;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public void setProductIdentifier(final String productIdentifier) {
        this.productIdentifier = productIdentifier;
    }

    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }

    public OrderLineDetails toOrderLineDetails() {
        return OrderLineFactory.create(new OrderLineId(identifier), new ProductId(productIdentifier), description, price, quantity);
    }

    public static OrderLine fromOrderLineDetails(OrderLineDetails details) {
        OrderLine line = new OrderLine();
        line.description = details.getDescription();
        line.identifier = details.getLineId().toString();
        line.productIdentifier = details.getProductId().toString();
        line.price = details.getPrice();
        line.quantity = details.getQuantity();
        return line;
    }

    @Override
    public String toString() {
        return "OrderLine [identifier=" + identifier + ", productIdentifier=" + productIdentifier + ", description="
                + description + ", price=" + price + ", quantity=" + quantity + "]";
    }
}

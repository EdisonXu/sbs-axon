package com.edi.learn.axon.events;

import com.edi.learn.axon.domain.OrderId;
import com.edi.learn.axon.domain.Product;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.Map;

/**
 * Created by Edison Xu on 2017/3/7.
 */
public class OrderCreatedEvent {

    @TargetAggregateIdentifier
    private OrderId orderId;
    private String username;
    private Map<String, Product> products;

    public OrderCreatedEvent(OrderId orderId, String username, Map<String, Product> products) {
        this.orderId = orderId;
        this.username = username;
        this.products = products;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public String getUsername() {
        return username;
    }

    public Map<String, Product> getProducts() {
        return products;
    }
}

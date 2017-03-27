package com.edi.learn.axon.commands;


import com.edi.learn.axon.domain.OrderId;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.Map;

/**
 * Created by Edison Xu on 2017/3/7.
 */
public class CreateOrderCommand {

    @TargetAggregateIdentifier
    private OrderId orderId;
    private String username;
    private Map<String, Integer> products;

    public CreateOrderCommand() {
    }

    public CreateOrderCommand(String username, Map<String, Integer> products) {
        this.orderId = new OrderId();
        this.username = username;
        this.products = products;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public String getUsername() {
        return username;
    }

    public Map<String, Integer> getProducts() {
        return products;
    }

    public void setOrderId(OrderId orderId) {
        this.orderId = orderId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setProducts(Map<String, Integer> products) {
        this.products = products;
    }
}

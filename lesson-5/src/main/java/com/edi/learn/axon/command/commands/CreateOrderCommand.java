package com.edi.learn.axon.command.commands;

import com.edi.learn.axon.common.domain.OrderId;

import java.util.Map;

/**
 * Created by Edison Xu on 2017/3/7.
 */
public class CreateOrderCommand {

    private OrderId orderId;
    private String username;
    private Map<String, Integer> products;

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
}

package com.edi.learn.axon.commands;


import com.edi.learn.axon.domain.OrderId;

/**
 * Created by Edison Xu on 2017/3/9.
 */
public class ReserveProductCommand {

    private OrderId orderId;
    private String productId;
    private int number;

    public ReserveProductCommand(OrderId orderId, String productId, int number) {
        this.orderId = orderId;
        this.productId = productId;
        this.number = number;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public String getProductId() {
        return productId;
    }

    public int getNumber() {
        return number;
    }
}

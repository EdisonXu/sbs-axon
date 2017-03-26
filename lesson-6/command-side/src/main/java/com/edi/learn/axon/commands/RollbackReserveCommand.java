package com.edi.learn.axon.commands;


import com.edi.learn.axon.domain.OrderId;

/**
 * Created by Edison Xu on 2017/3/15.
 */
public class RollbackReserveCommand {

    private OrderId orderId;
    private String productId;
    private int number;

    public RollbackReserveCommand(OrderId orderId, String productId, int number) {
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

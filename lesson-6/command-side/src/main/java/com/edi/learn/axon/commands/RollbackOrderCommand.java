package com.edi.learn.axon.commands;


import com.edi.learn.axon.domain.OrderId;

/**
 * Created by Edison Xu on 2017/3/15.
 */
public class RollbackOrderCommand {
    private OrderId orderId;
    public RollbackOrderCommand(OrderId orderId) {
        this.orderId = orderId;
    }

    public OrderId getOrderId() {
        return orderId;
    }
}

package com.edi.learn.cloud.commands.order;


import com.edi.learn.cloud.domain.OrderId;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Created by Edison Xu on 2017/3/15.
 */
public class RollbackOrderCommand {
    @TargetAggregateIdentifier
    private OrderId orderId;

    public RollbackOrderCommand() {
    }

    public RollbackOrderCommand(OrderId orderId) {
        this.orderId = orderId;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public void setOrderId(OrderId orderId) {
        this.orderId = orderId;
    }
}

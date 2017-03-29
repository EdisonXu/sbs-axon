package com.edi.learn.cloud.commands.product;


import com.edi.learn.cloud.domain.OrderId;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Created by Edison Xu on 2017/3/9.
 */
public class ReserveProductCommand {

    @TargetAggregateIdentifier
    private OrderId orderId;
    private String productId;
    private int number;

    public ReserveProductCommand() {
    }

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

    public void setOrderId(OrderId orderId) {
        this.orderId = orderId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

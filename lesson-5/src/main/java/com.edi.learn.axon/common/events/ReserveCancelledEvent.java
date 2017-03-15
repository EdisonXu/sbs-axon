package com.edi.learn.axon.common.events;

import com.edi.learn.axon.common.domain.OrderId;

/**
 * Created by Edison Xu on 2017/3/15.
 */
public class ReserveCancelledEvent {

    private OrderId orderId;
    private String productId;
    private int stock;

    public ReserveCancelledEvent(OrderId orderId, String productId, int stock) {
        this.orderId = orderId;
        this.productId = productId;
        this.stock = stock;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public String getProductId() {
        return productId;
    }

    public int getStock() {
        return stock;
    }
}

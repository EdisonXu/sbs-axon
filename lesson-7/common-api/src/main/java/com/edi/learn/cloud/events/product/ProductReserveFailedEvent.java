package com.edi.learn.cloud.events.product;


import com.edi.learn.cloud.domain.OrderId;

/**
 * Created by Edison Xu on 2017/3/15.
 */
public class ProductReserveFailedEvent {

    private OrderId orderId;
    private String productId;

    public ProductReserveFailedEvent() {
    }

    public ProductReserveFailedEvent(OrderId orderId, String productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public String getProductId() {
        return productId;
    }
}

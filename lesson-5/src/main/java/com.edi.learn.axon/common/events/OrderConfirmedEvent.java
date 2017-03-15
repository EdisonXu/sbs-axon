package com.edi.learn.axon.common.events;

import com.edi.learn.axon.common.domain.OrderId;

/**
 * Created by Edison Xu on 2017/3/15.
 */
public class OrderConfirmedEvent {
    private OrderId id;
    public OrderConfirmedEvent(OrderId id) {
        this.id = id;
    }

    public OrderId getId() {
        return id;
    }
}

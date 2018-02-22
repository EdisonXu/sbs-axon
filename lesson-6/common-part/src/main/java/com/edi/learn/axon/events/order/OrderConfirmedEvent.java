package com.edi.learn.axon.events.order;


import com.edi.learn.axon.domain.OrderId;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Created by Edison Xu on 2017/3/15.
 */
public class OrderConfirmedEvent {
    @TargetAggregateIdentifier
    private OrderId id;

    public OrderConfirmedEvent() {
    }

    public OrderConfirmedEvent(OrderId id) {
        this.id = id;
    }

    public OrderId getId() {
        return id;
    }
}

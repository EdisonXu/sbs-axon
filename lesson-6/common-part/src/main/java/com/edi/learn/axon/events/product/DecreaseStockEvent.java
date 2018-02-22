package com.edi.learn.axon.events.product;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Created by Edison Xu on 2017/4/10.
 */
public class DecreaseStockEvent {

    @TargetAggregateIdentifier
    protected String id;
    protected int number;

    public DecreaseStockEvent() {
    }

    public DecreaseStockEvent(String id, int number) {
        this.id = id;
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }
}

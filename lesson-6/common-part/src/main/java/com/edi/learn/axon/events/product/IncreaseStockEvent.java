package com.edi.learn.axon.events.product;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Created by Edison Xu on 2017/4/10.
 */
public class IncreaseStockEvent {

    @TargetAggregateIdentifier
    protected String id;
    protected int number;

    public IncreaseStockEvent() {
    }

    public IncreaseStockEvent(String id, int number) {
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

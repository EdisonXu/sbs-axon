package com.edi.learn.axon.command.commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Created by Edison Xu on 2017/3/9.
 */
public class ReserveStockCommand {

    @TargetAggregateIdentifier
    private String productId;
    private long number;

    public ReserveStockCommand(String productId, long number) {
        this.productId = productId;
        this.number = number;
    }

    public String getProductId() {
        return productId;
    }

    public long getNumber() {
        return number;
    }
}

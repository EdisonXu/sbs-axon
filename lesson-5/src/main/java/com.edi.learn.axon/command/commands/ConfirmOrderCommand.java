package com.edi.learn.axon.command.commands;

import com.edi.learn.axon.common.domain.OrderId;

/**
 * Created by Edison Xu on 2017/3/15.
 */
public class ConfirmOrderCommand {

    private OrderId id;

    public ConfirmOrderCommand(OrderId id) {
        this.id = id;
    }

    public OrderId getId() {
        return id;
    }
}

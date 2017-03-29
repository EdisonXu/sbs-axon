package com.edi.learn.cloud.commands.order;


import com.edi.learn.cloud.domain.OrderId;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Created by Edison Xu on 2017/3/15.
 */
public class ConfirmOrderCommand {

    @TargetAggregateIdentifier
    private OrderId id;

    public ConfirmOrderCommand() {
    }

    public ConfirmOrderCommand(OrderId id) {
        this.id = id;
    }

    public OrderId getId() {
        return id;
    }

}

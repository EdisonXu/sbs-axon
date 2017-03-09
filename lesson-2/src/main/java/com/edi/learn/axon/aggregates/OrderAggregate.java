package com.edi.learn.axon.aggregates;

import com.edi.learn.axon.commands.CreateOrderCommand;
import com.edi.learn.axon.domain.OrderId;
import com.edi.learn.axon.events.OrderCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateMember;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Map;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

/**
 * Created by Edison Xu on 2017/3/7.
 */
@Aggregate
public class OrderAggregate {

    @AggregateIdentifier
    private OrderId id;
    private String username;

    @AggregateMember
    private Map<String, Long> products;

    public OrderAggregate(){}

    @CommandHandler
    public OrderAggregate(CreateOrderCommand command){
        apply(new OrderCreatedEvent(command.getOrderId(), command.getUsername(), command.getProducts()));
    }

    public OrderId getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Map<String, Long> getProductList() {
        return products;
    }

    @EventHandler
    public void on(OrderCreatedEvent event){
        this.id = event.getOrderId();
        this.username = event.getUsername();
        this.products = event.getProducts();
    }

}

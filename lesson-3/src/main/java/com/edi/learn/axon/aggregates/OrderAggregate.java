package com.edi.learn.axon.aggregates;

import com.edi.learn.axon.domain.OrderId;
import com.edi.learn.axon.domain.Product;
import com.edi.learn.axon.events.OrderCreatedEvent;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateMember;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Map;

/**
 * Created by Edison Xu on 2017/3/7.
 */
@Aggregate
public class OrderAggregate {

    @AggregateIdentifier
    private OrderId id;
    private String username;

    @AggregateMember
    private Map<String, Product> products;

    public OrderAggregate(){}

    public OrderAggregate(OrderId id, String username, Map<String, Product> products) {
        this.id = id;
        this.username = username;
        this.products = products;
    }

    public OrderId getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Map<String, Product> getProductList() {
        return products;
    }

    @EventHandler
    public void on(OrderCreatedEvent event){
        this.id = event.getOrderId();
        this.username = event.getUsername();
        this.products = event.getProducts();
    }

}

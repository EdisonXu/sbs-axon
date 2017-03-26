package com.edi.learn.axon.handlers;

import com.edi.learn.axon.entries.OrderEntry;
import com.edi.learn.axon.entries.OrderProductEntry;
import com.edi.learn.axon.events.order.OrderCancelledEvent;
import com.edi.learn.axon.events.order.OrderConfirmedEvent;
import com.edi.learn.axon.events.order.OrderCreatedEvent;
import com.edi.learn.axon.repository.OrderEntryRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * This event handler is used to update the repository data of the query side in the CQRS.
 * Created by Edison Xu on 2017/3/15.
 */
@Component
@ProcessingGroup("order")
public class OrderEventHandler {

    private static final Logger LOGGER = getLogger(OrderEventHandler.class);

    @Autowired
    private OrderEntryRepository repository;

    @EventHandler
    public void on(OrderCreatedEvent event){
        Map<String, OrderProductEntry> map = new HashMap<>();
        event.getProducts().forEach((id, product)->{
            map.put(id,
                    new OrderProductEntry(
                            product.getId(),
                            product.getName(),
                            product.getPrice(),
                            product.getAmount()));
        });
        OrderEntry order = new OrderEntry(event.getOrderId().toString(), event.getUsername(), map);
        repository.save(order);
    }

    @EventHandler
    public void on(OrderConfirmedEvent event){
        OrderEntry order = repository.findOne(event.getId().getIdentifier());
        if(order==null){
            LOGGER.error("Cannot find order with id {}", order.getId());
            return;
        }
        order.setStatus("confirmed");
        repository.save(order);
    }

    @EventHandler
    public void on(OrderCancelledEvent event){
        OrderEntry order = repository.findOne(event.getId().getIdentifier());
        if(order==null){
            LOGGER.error("Cannot find order with id {}", order.getId());
            return;
        }
        order.setStatus("cancelled");
        repository.save(order);
    }
}

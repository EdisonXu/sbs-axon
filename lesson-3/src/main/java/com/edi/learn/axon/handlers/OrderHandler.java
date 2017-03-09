package com.edi.learn.axon.handlers;

import com.edi.learn.axon.aggregates.OrderAggregate;
import com.edi.learn.axon.commands.CreateOrderCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventhandling.EventBus;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Edison on 2017/3/9.
 */
public class OrderHandler {

    private static final Logger LOGGER = getLogger(OrderHandler.class);

    @Autowired
    private Repository<OrderAggregate> repository;
    @Autowired
    private EventBus eventBus;

    @CommandHandler
    public void handle(CreateOrderCommand command){
        command.getProducts().forEach((productId,number)->{
           repository.load(productId);
        });
    }


}

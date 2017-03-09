package com.edi.learn.axon.transaction;

import com.edi.learn.axon.aggregates.OrderCreatedEvent;
import com.edi.learn.axon.commands.ReserveStockCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Edison Xu on 2017/3/9.
 */
@Saga
public class OrderSaga {

    private static final Logger LOGGER = getLogger(OrderSaga.class);

    @Autowired
    private CommandGateway gateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderCreatedEvent event){
        LOGGER.debug("a new transaction is started with identifier {} ", event.getOrderId());

        //reserve stock
        event.getProducts().forEach((k,v)->{
            gateway.send(new ReserveStockCommand(k,v));
        });
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(){

    }

}

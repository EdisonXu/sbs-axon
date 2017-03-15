package com.edi.learn.axon.command.saga;

import com.edi.learn.axon.command.commands.ConfirmOrderCommand;
import com.edi.learn.axon.command.commands.ReserveProductCommand;
import com.edi.learn.axon.command.commands.RollbackOrderCommand;
import com.edi.learn.axon.command.commands.RollbackReserveCommand;
import com.edi.learn.axon.common.domain.OrderId;
import com.edi.learn.axon.common.domain.OrderProduct;
import com.edi.learn.axon.common.events.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.saga.EndSaga;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Edison Xu on 2017/3/15.
 */
@Saga
public class OrderSaga {

    private static final Logger LOGGER = getLogger(OrderSaga.class);

    private OrderId orderIdentifier;
    //TODO: use ConcurrentHashMap instead?
    private Map<String, OrderProduct> toReserve;
    private Map<String, OrderProduct> reserved;

    @Autowired
    private CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderCreatedEvent event){
        this.orderIdentifier = event.getOrderId();
        this.toReserve = event.getProducts();
        reserved = new HashMap<>();
        event.getProducts().forEach((id,product)->{
            ReserveProductCommand command = new ReserveProductCommand(orderIdentifier, id, product.getAmount());
            commandGateway.send(command);
        });
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(ProductNotEnoughEvent event){
        LOGGER.info("No enough item to buy");
        if(reserved.size() == toReserve.size()){
            reserved.forEach((id,product)->{
                commandGateway.send(new RollbackReserveCommand(event.getOrderId(), id, product.getAmount()));
            });
        }else
            commandGateway.send(new RollbackOrderCommand(event.getOrderId()));
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(ReserveCancelledEvent event){
        reserved.remove(event.getProductId());
        if(reserved.isEmpty())
            commandGateway.send(new RollbackOrderCommand(event.getOrderId()));
    }

    @SagaEventHandler(associationProperty = "id", keyName = "orderId")
    @EndSaga
    public void handle(OrderCancelledEvent event){
        LOGGER.info("Order {} is cancelled", event.getId());
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(ProductReservedEvent event){
        OrderProduct reservedProduct = toReserve.get(event.getProductId());
        reserved.put(event.getProductId(), reservedProduct);
        //Q: will a concurrent issue raise?
        if(reserved.size() == toReserve.size())
            commandGateway.send(new ConfirmOrderCommand(orderIdentifier));
    }

    @SagaEventHandler(associationProperty = "id", keyName = "orderId")
    @EndSaga
    public void handle(OrderConfirmedEvent event){
        LOGGER.info("Order {} is confirmed", event.getId());
    }

}

package com.edi.learn.cloud.command.handlers;

import com.edi.learn.cloud.command.aggregate.OrderAggregate;
import com.edi.learn.cloud.common.web.clients.ProductService;
import com.edi.learn.cloud.commands.order.ConfirmOrderCommand;
import com.edi.learn.cloud.commands.order.CreateOrderCommand;
import com.edi.learn.cloud.commands.order.RollbackOrderCommand;
import com.edi.learn.cloud.common.web.dto.ProductDto;
import com.edi.learn.cloud.domain.OrderProduct;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventhandling.EventBus;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Edison on 2017/3/9.
 */
@Component
public class OrderHandler {

    private static final Logger LOGGER = getLogger(OrderHandler.class);

    @Autowired
    private Repository<OrderAggregate> repository;

    @Autowired
    private ProductService productService;

    @Autowired
    private EventBus eventBus;

    @CommandHandler
    public void handle(CreateOrderCommand command) throws Exception {
        Map<String, OrderProduct> products = new HashMap<>();
        command.getProducts().forEach((productId,number)->{
            LOGGER.debug("Loading product information with productId: {}",productId);
            //Aggregate<ProductAggregate> aggregate = productRepository.load(productId);
            ProductDto productDto = productService.getProduct(productId);
            products.put(productId,
                    new OrderProduct(productId,
                            productDto.getName(),
                            productDto.getPrice(),
                            number));
        });
        repository.newInstance(() -> new OrderAggregate(command.getOrderId(), command.getUsername(), products));
    }


    @CommandHandler
    public void handle(RollbackOrderCommand command){
        Aggregate<OrderAggregate> aggregate = repository.load(command.getOrderId().getIdentifier());
        aggregate.execute(aggregateRoot->aggregateRoot.delete());
    }

    @CommandHandler
    public void handle(ConfirmOrderCommand command){
        Aggregate<OrderAggregate> aggregate = repository.load(command.getId().getIdentifier());
        aggregate.execute(aggregateRoot->aggregateRoot.confirm());
    }

}

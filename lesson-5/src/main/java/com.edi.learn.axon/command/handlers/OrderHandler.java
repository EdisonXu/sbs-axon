package com.edi.learn.axon.command.handlers;

import com.edi.learn.axon.command.aggregates.OrderAggregate;
import com.edi.learn.axon.command.commands.CreateOrderCommand;
import com.edi.learn.axon.common.domain.OrderProduct;
import com.edi.learn.axon.query.entries.ProductEntry;
import com.edi.learn.axon.query.repository.ProductEntryRepository;
import org.axonframework.commandhandling.CommandHandler;
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
    private ProductEntryRepository productQueryRepository;

    @Autowired
    private EventBus eventBus;

    @CommandHandler
    public void handle(CreateOrderCommand command) throws Exception {
        Map<String, OrderProduct> products = new HashMap<>();
        command.getProducts().forEach((productId,number)->{
            LOGGER.debug("Loading product information with productId: {}",productId);
            ProductEntry entry = productQueryRepository.findOne(productId);
            products.put(productId, new OrderProduct(productId, entry.getName(), entry.getPrice(), number));
        });
        repository.newInstance(() -> new OrderAggregate(command.getOrderId(), command.getUsername(), products));
    }
}

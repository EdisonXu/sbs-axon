package com.edi.learn.cloud.command.handlers;

import com.edi.learn.cloud.command.aggregates.ProductAggregate;
import com.edi.learn.cloud.commands.product.CreateProductCommand;
import com.edi.learn.cloud.commands.product.ReserveProductCommand;
import com.edi.learn.cloud.commands.product.RollbackReserveCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.Repository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Edison Xu on 2017/3/15.
 */
@Component
public class ProductHandler {

    private static final Logger LOGGER = getLogger(ProductHandler.class);

    @Autowired
    private Repository<ProductAggregate> repository;

    @CommandHandler
    public void on(CreateProductCommand command) throws Exception {
        repository.newInstance(()->new ProductAggregate(command.getId(), command.getName(), command.getStock(), command.getPrice()));
    }

    @CommandHandler
    public void on(ReserveProductCommand command){
        Aggregate<ProductAggregate> aggregate = repository.load(command.getProductId());
        aggregate.execute(aggregateRoot->aggregateRoot.reserve(command.getOrderId(), command.getNumber()));
    }

    @CommandHandler
    public void on(RollbackReserveCommand command){
        Aggregate<ProductAggregate> aggregate = repository.load(command.getProductId());
        aggregate.execute(aggregateRoot->aggregateRoot.cancellReserve(command.getOrderId(), command.getNumber()));
    }
}

package com.edi.learn.cloud.query.handlers;

import com.edi.learn.cloud.events.product.ProductCreatedEvent;
import com.edi.learn.cloud.events.product.ProductReservedEvent;
import com.edi.learn.cloud.events.product.ReserveCancelledEvent;
import com.edi.learn.cloud.query.entries.ProductEntry;
import com.edi.learn.cloud.query.repository.ProductEntryRepository;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * This event handler is used to update the repository data of the query side in the CQRS.
 * Created by Edison Xu on 2017/3/7.
 */
@Component
public class ProductEventHandler {

    private static final Logger LOGGER = getLogger(ProductEventHandler.class);

    @Autowired
    ProductEntryRepository repository;

    @EventHandler
    public void on(ProductCreatedEvent event){
        // update the data in the cache or db of the query side
        LOGGER.debug("repository data is updated");
        repository.save(new ProductEntry(event.getId(), event.getName(), event.getPrice(), event.getStock()));
    }

    @EventHandler
    public void on(ProductReservedEvent event){
        ProductEntry product = repository.findOne(event.getProductId());
        if(product==null){
            LOGGER.error("Cannot find product with id {}", product.getId());
            return;
        }
        product.setStock(product.getStock()-event.getAmount());
        repository.save(product);
    }

    @EventHandler
    public void on(ReserveCancelledEvent event){
        ProductEntry product = repository.findOne(event.getProductId());
        if(product==null){
            LOGGER.error("Cannot find product with id {}", product.getId());
            return;
        }
        product.setStock(product.getStock()+event.getAmount());
        repository.save(product);
    }
}

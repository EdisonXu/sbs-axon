package com.edi.learn.axon.query.handlers;

import com.edi.learn.axon.events.ProductCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * This event handler is used to update the repository data of the query side in the CQRS.
 * Created by Edison Xu on 2017/3/7.
 */
@Component
public class ProductEventHandler {

    private static final Logger LOGGER = getLogger(ProductEventHandler.class);

    @EventHandler
    public void handle(ProductCreatedEvent event){
        // update the data in the cache or db of the query side
        LOGGER.debug("repository data is updated");
    }
}

package com.edi.learn.cloud.command.config;

import com.edi.learn.cloud.command.aggregates.ProductAggregate;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Edison on 2017/3/28.
 */
@Configuration
public class PersistenceConfiguration {

    @Bean(name = "productRepository")
    public Repository<ProductAggregate> repository(EventStore eventStore){
        return new EventSourcingRepository<ProductAggregate>(ProductAggregate.class, eventStore);
    }
}

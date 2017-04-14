package com.edi.learn.cloud.command.config;

import com.edi.learn.cloud.command.aggregate.OrderAggregate;
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

    @Bean()
    public Repository<OrderAggregate> repository(EventStore eventStore){
        return new EventSourcingRepository<OrderAggregate>(OrderAggregate.class, eventStore);
    }
}

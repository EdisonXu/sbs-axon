package com.edi.learn.axon.main;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Edison Xu on 2017/3/7.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.edi.learn"})
@EntityScan(basePackages = {"com.edi.learn",
        "org.axonframework.eventsourcing.eventstore.jpa",
        "org.axonframework.eventhandling.saga.repository.jpa",
        "org.axonframework.eventhandling.tokenstore.jpa"})
//@EnableJpaRepositories(basePackages = {"com.edi.learn"})
@EnableMongoRepositories(basePackages = {"com.edi.learn"})
public class Application {

    private static final Logger LOGGER = getLogger(Application.class);

    public static void main(String args[]){
        SpringApplication.run(Application.class, args);
    }
}

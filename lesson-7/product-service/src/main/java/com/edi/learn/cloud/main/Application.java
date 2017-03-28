package com.edi.learn.cloud.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Edison Xu on 2017/3/28.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.edi.learn"})
@EntityScan(basePackages = {"com.edi.learn",
        "org.axonframework.eventsourcing.eventstore.jpa",
        "org.axonframework.eventhandling.saga.repository.jpa",
        "org.axonframework.eventhandling.tokenstore.jpa"})
public class Application {

    public static void main(String args[]){
        SpringApplication.run(Application.class, args);
    }
}

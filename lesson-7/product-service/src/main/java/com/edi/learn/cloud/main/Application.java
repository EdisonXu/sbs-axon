package com.edi.learn.cloud.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by Edison Xu on 2017/3/28.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.edi.learn"})
@EnableJpaRepositories(basePackages = {"com.edi.learn.cloud.command"})
@EnableMongoRepositories(basePackages = {"com.edi.learn.cloud.query"})
public class Application {

    public static void main(String args[]){
        SpringApplication.run(Application.class, args);
    }
}

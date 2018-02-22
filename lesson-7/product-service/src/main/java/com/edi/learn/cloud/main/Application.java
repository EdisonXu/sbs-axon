package com.edi.learn.cloud.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by Edison Xu on 2017/3/28.
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.edi.learn"})
@EnableJpaRepositories(basePackages = {"com.edi.learn.cloud.command"})
@EnableMongoRepositories(basePackages = {"com.edi.learn.cloud.query"})
@EnableAutoConfiguration()
public class Application {

    public static void main(String args[]){
        SpringApplication.run(Application.class, args);
    }
}

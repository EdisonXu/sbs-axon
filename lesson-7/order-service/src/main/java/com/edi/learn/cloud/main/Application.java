package com.edi.learn.cloud.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.hateoas.config.EnableHypermediaSupport;

/**
 * Created by Edison Xu on 2017/3/28.
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.edi.learn"})
@EnableJpaRepositories(basePackages = {"com.edi.learn.cloud.command"})
@EnableMongoRepositories(basePackages = {"com.edi.learn.cloud.query"})
@EnableFeignClients(basePackages = {"com.edi.learn.cloud.common.web"})
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class Application {

    public static void main(String args[]){
        SpringApplication.run(Application.class, args);
    }
}


package com.edi.learn.cloud.command.config;

import org.axonframework.commandhandling.distributed.CommandBusConnector;
import org.axonframework.commandhandling.distributed.CommandRouter;
import org.axonframework.commandhandling.distributed.DistributedCommandBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Edison Xu on 2017/4/13.
 */
@Configuration
public class CloudConfiguration {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    @Primary
    public DistributedCommandBus distributedCommandBus(CommandRouter router, CommandBusConnector connector){
        return new DistributedCommandBus(router, connector);
    }
}

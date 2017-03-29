package com.edi.learn.cloud.command.config;

import org.axonframework.boot.AxonAutoConfiguration;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.distributed.AnnotationRoutingStrategy;
import org.axonframework.commandhandling.distributed.CommandBusConnector;
import org.axonframework.commandhandling.distributed.CommandRouter;
import org.axonframework.commandhandling.distributed.DistributedCommandBus;
import org.axonframework.serialization.Serializer;
import org.axonframework.springcloud.commandhandling.SpringCloudCommandRouter;
import org.axonframework.springcloud.commandhandling.SpringHttpCommandBusConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Edison Xu on 2017/3/29.
 */
@ConditionalOnBean(value = { DiscoveryClient.class, RestTemplate.class })
@EnableConfigurationProperties(SpringCloudConfiguration.DistributedCommandBusProperties.class)
@ConditionalOnProperty("axon.distributed.enabled")
@AutoConfigureAfter(AxonAutoConfiguration.JpaConfiguration.class)
@Configuration
public class SpringCloudConfiguration {

    @Autowired
    private DistributedCommandBusProperties distributedCommandBusProperties;

    @ConditionalOnMissingBean
    @Primary
    @Bean
    public DistributedCommandBus distributedCommandBus(CommandRouter router, CommandBusConnector connector) {
        DistributedCommandBus commandBus = new DistributedCommandBus(router, connector);
        commandBus.updateLoadFactor(distributedCommandBusProperties.getLoadFactor());
        return commandBus;
    }

    @ConditionalOnMissingBean(CommandRouter.class)
    @Bean
    public SpringCloudCommandRouter springCloudCommandRouter(DiscoveryClient discoveryClient) {
        return new SpringCloudCommandRouter(discoveryClient, new AnnotationRoutingStrategy());
    }

    @ConditionalOnMissingBean(CommandBusConnector.class)
    @Bean
    public SpringHttpCommandBusConnector springHttpCommandBusConnector(@Qualifier("localSegment") CommandBus localSegment,
                                                                       @Qualifier("restTemplate") RestTemplate restTemplate,
                                                                       Serializer serializer) {
        return new SpringHttpCommandBusConnector(localSegment, restTemplate, serializer);
    }

    @ConfigurationProperties(prefix = "axon.distributed")
    public static class DistributedCommandBusProperties {

        /**
         * Enables DistributedCommandBus configuration for this application
         */
        private boolean enabled = false;

        /**
         * Sets the loadFactor for this node to join with. The loadFactor sets the relative load this node will
         * receive compared to other nodes in the cluster. Defaults to 100.
         */
        private int loadFactor = 100;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }


        public int getLoadFactor() {
            return loadFactor;
        }

        public void setLoadFactor(int loadFactor) {
            this.loadFactor = loadFactor;
        }

    }
}

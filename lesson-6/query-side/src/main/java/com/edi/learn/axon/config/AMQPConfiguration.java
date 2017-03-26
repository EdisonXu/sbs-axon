package com.edi.learn.axon.config;

import com.rabbitmq.client.Channel;
import org.axonframework.amqp.eventhandling.spring.SpringAMQPMessageSource;
import org.axonframework.serialization.Serializer;
import org.slf4j.Logger;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Edison on 2017/3/25.
 */
@Configuration
public class AMQPConfiguration {

    private static final Logger LOGGER = getLogger(AMQPConfiguration.class);

    /*@Bean
    public Queue queue(){
        return new Queue("axon", true);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("axon");
    }

    // can also defined in the application.properties
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange("Axon.EventBus");
    }*/

    @Bean
    public SpringAMQPMessageSource productQueueMessageSource(Serializer serializer){
        return new SpringAMQPMessageSource(serializer){
            @RabbitListener(queues = "product")
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                LOGGER.debug("Product message received: "+message.toString());
                super.onMessage(message, channel);
            }
        };
    }

    @Bean
    public SpringAMQPMessageSource orderQueueMessageSource(Serializer serializer){
        return new SpringAMQPMessageSource(serializer){
            @RabbitListener(queues = "order")
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                LOGGER.debug("Order message received: "+message.toString());
                super.onMessage(message, channel);
            }
        };
    }
}

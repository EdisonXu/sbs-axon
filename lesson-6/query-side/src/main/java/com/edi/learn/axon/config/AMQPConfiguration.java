package com.edi.learn.axon.config;

import com.rabbitmq.client.Channel;
import org.axonframework.amqp.eventhandling.spring.SpringAMQPMessageSource;
import org.axonframework.serialization.Serializer;
import org.slf4j.Logger;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Edison on 2017/3/25.
 */
@Configuration
public class AMQPConfiguration {

    private static final Logger LOGGER = getLogger(AMQPConfiguration.class);

    @Value("${axon.amqp.exchange}")
    private String exchangeName;

    @Bean
    public Queue productQueue(){
        return new Queue("product", false);
    }

    @Bean
    public Queue orderQueue(){
        return new Queue("order",false);
    }

    @Bean
    public Exchange exchange(){
        return ExchangeBuilder.topicExchange(exchangeName).durable(false).build();
    }

    @Bean
    public Binding productQueueBinding() {
        return BindingBuilder.bind(productQueue()).to(exchange()).with("#.product.#").noargs();
    }

    @Bean
    public Binding orderQueueBinding() {
        return BindingBuilder.bind(orderQueue()).to(exchange()).with("#.order.#").noargs();
    }

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

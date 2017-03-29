package com.edi.learn.cloud.command.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Edison on 2017/3/25.
 */
@Configuration
public class AMQPConfiguration {

    @Bean
    public Queue productQueue(){
        return new Queue("product", true);
    }

    @Bean
    public Queue orderQueue(){
        return new Queue("order",true);
    }

    @Bean
    public Exchange exchange(){
        return ExchangeBuilder.topicExchange("Axon.EventBus").durable(true).build();
    }

    @Bean
    public Binding productQueueBinding() {
        return BindingBuilder.bind(productQueue()).to(exchange()).with("#.product.#").noargs();
    }

    @Bean
    public Binding orderQueueBinding() {
        return BindingBuilder.bind(orderQueue()).to(exchange()).with("#.order.#").noargs();
    }

    /*@Bean
    public SpringAMQPMessageSource myQueueMessageSource(AMQPMessageConverter messageConverter){
        return new SpringAMQPMessageSource(messageConverter){

            @RabbitListener(queues = "axon")
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                super.onMessage(message, channel);
            }
        };
    }*/
}

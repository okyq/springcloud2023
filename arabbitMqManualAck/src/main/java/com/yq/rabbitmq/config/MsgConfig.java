package com.yq.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuqian
 * @ClassName MsgConfig
 * @description:
 * @date 2023年03月14日
 */
@Configuration
public class MsgConfig {

    public static final String MANUAL_EXCHANGE_NAME = "manual.exchange";
    public static final String MANUAL_QUEUE_NAME = "manual.queue";
    public static final String MANUAL_ROUTING_KEY  = "key1";

    @Bean("manualExchange")
    public DirectExchange manualExchange(){
        return new DirectExchange(MANUAL_EXCHANGE_NAME);
    }

    @Bean("manualQueue")
    public Queue manualQueue(){
    return new Queue(MANUAL_QUEUE_NAME);
    }

    @Bean
    public Binding bind(@Qualifier("manualExchange") DirectExchange manualExchange, @Qualifier("manualQueue") Queue manualQueue){
        return BindingBuilder.bind(manualQueue).to(manualExchange).with(MANUAL_ROUTING_KEY);
    }
}

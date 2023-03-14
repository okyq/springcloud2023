package com.yq.rabbit.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuqian
 * @ClassName PriorityConfig
 * @description: 设置消费优先级
 * @date 2023年03月14日
 */
@Configuration
public class PriorityConfig {

    public static final String PRIORITY_EXCHANGE_NAME = "priority.exchange";
    public static final String PRIORITY_QUEUE_NAME = "priority.queue";
    public static final String PRIORITY_ROUTING_KEY  = "key1";

    @Bean("priorityExchange")
    public DirectExchange priorityExchange(){
        return new DirectExchange(PRIORITY_EXCHANGE_NAME);
    }

    @Bean("priorityQueue")
    public Queue priorityQueue(){
        return QueueBuilder.durable(PRIORITY_QUEUE_NAME).maxPriority(10).build();
    }

    @Bean
    public Binding bindingQueueAndExchange(@Qualifier("priorityQueue") Queue queue, @Qualifier("priorityExchange") DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with(PRIORITY_ROUTING_KEY);
    }

}

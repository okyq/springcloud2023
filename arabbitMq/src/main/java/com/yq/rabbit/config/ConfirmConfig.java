package com.yq.rabbit.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuqian
 * @ClassName ConfirmConfig
 * @description:
 * @date 2023年03月14日
 */
@Configuration
public class ConfirmConfig {

    public static final String CONFIRM_EXCHANGE_NAME = "confirm.exchange";
    public static final String CONFIRM_QUEUE_NAME = "confirm.queue";
    public static final String CONFIRM_ROUTING_KEY  = "key1";

    //备份交换机所需信息
    public static final String BACKUP_EXCHANGE_NAME = "backup.exchange";
    public static final String BACKUP_QUEUE_NAME = "backup.queue";
    public static final String WARN_QUEUE_NAME = "warning.queue";

    @Bean("confirmExchange")
    public DirectExchange confirmExchange(){
        return ExchangeBuilder.directExchange(CONFIRM_EXCHANGE_NAME).durable(true).alternate(BACKUP_EXCHANGE_NAME).build();
    }

    @Bean("confirmQueue")
    public Queue confirmQueue(){
        return new Queue(CONFIRM_QUEUE_NAME);
    }

    @Bean
    public Binding BindingExchangeAndQueue(@Qualifier("confirmExchange") DirectExchange directExchange,
                                           @Qualifier("confirmQueue") Queue queue){
        return BindingBuilder.bind(queue).to(directExchange).with(CONFIRM_ROUTING_KEY);
    }

    @Bean("backupExchange")
    public FanoutExchange backupExchange() {
        return  new FanoutExchange(BACKUP_EXCHANGE_NAME);
    }

    @Bean("backupQueue")
    public Queue backupQueue(){
        return new Queue(BACKUP_QUEUE_NAME);
    }

    @Bean("warnQueue")
    public Queue warnQueue(){
        return new Queue(WARN_QUEUE_NAME);
    }

    @Bean
    public Binding BindingBackupQueue(@Qualifier("backupExchange") FanoutExchange fanoutExchange, @Qualifier("backupQueue") Queue queue){
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

    @Bean
    public Binding BindingWarnQueue(@Qualifier("backupExchange") FanoutExchange fanoutExchange, @Qualifier("warnQueue") Queue queue){
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }
}

package com.yq.rabbit.controller;

import com.yq.rabbit.config.ConfirmConfig;
import com.yq.rabbit.config.PriorityConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author yuqian
 * @ClassName SendMessageController
 * @description:
 * @date 2023年03月12日
 */
@RestController
@Slf4j
public class SendMessageController {
    public static final String DELAYED_QUEUE_NAME = "delayed.queue";
    public static final String DELAYED_EXCHANGE_NAME = "delayed.exchange";
    public static final String DELAYED_ROUTING_KEY = "delayed.routingkey";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/send/{message}")
    public String sendMessage(@PathVariable String message) {
        log.info("当前时间：{},发送一条信息给两个 TTL 队列:{}", new Date(), message);
        rabbitTemplate.convertAndSend("X", "XA", "消息来自 ttl 为 10S 的队列: " + message);
        rabbitTemplate.convertAndSend("X", "XB", "消息来自 ttl 为 40S 的队列: " + message);
        return "success";
    }

    @GetMapping("/sendDelay/{message}/{ttl}")
    public String sendDelayMessage(@PathVariable String message,@PathVariable Integer ttl) {
        log.info("当前时间：{},发送一条信息{}给队列，延迟时间为：{}", new Date(), message,ttl);
        rabbitTemplate.convertAndSend(DELAYED_EXCHANGE_NAME, DELAYED_ROUTING_KEY, "收到延迟"+ttl+"s的消息: " + message,correlationData -> {
            correlationData.getMessageProperties().setDelay(ttl);
            return correlationData;
        });
        return "success";
    }

    /**  
      * @description: 发布确认--发送正确的消息
      * @author yuqian
      * @params [message]
      * @date 2023/3/14
      * @return java.lang.String
      */ 
    @GetMapping("/send/confirm/{flag}/{message}")
    public String sendFirmMessage(@PathVariable Integer flag,@PathVariable String message) {
        if(flag>0) {
            log.info("当前时间：{},发送一条正确消息:{}", new Date(), message);
            rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE_NAME, ConfirmConfig.CONFIRM_ROUTING_KEY, "发送的消息: " + message);
        } else if (flag == 0) {
            log.info("当前时间：{},发送一条错误交换机的信息息:{}", new Date(), message);
            rabbitTemplate.convertAndSend("fasefeaf", ConfirmConfig.CONFIRM_ROUTING_KEY, "发送的消息: " + message);
        } else {
            log.info("当前时间：{},发送一条错误RoutingKey的消息:{}", new Date(), message);
            rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE_NAME, "afeaf", "发送的消息: " + message);
        }
        return "success";
    }

    /**
      * @description: 消息优先级测试
      * @author yuqian
      * @params [flag, message]
      * @date 2023/3/14
      * @return java.lang.String
      */
    @GetMapping("/send/pri")
    public String sendpri() {
        for (int i = 0; i < 10; i++) {
            String msg = "info" + i;
            if(i % 2 == 0){
                rabbitTemplate.convertAndSend(PriorityConfig.PRIORITY_EXCHANGE_NAME,PriorityConfig.PRIORITY_ROUTING_KEY,msg, message -> {
                    message.getMessageProperties().setPriority(5);
                    return message;
                });
            } else {
                rabbitTemplate.convertAndSend(PriorityConfig.PRIORITY_EXCHANGE_NAME,PriorityConfig.PRIORITY_ROUTING_KEY,msg, message -> {
                    message.getMessageProperties().setPriority(1);
                    return message;
                });
            }
        }
        return "success";
    }
}

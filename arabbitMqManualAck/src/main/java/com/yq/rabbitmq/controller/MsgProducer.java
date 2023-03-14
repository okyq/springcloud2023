package com.yq.rabbitmq.controller;

import com.yq.rabbitmq.config.MsgConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuqian
 * @ClassName MsgProducer
 * @description:
 * @date 2023年03月14日
 */
@RestController
public class MsgProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/send/{msg}")
    public String sendMessage(@PathVariable("msg") String msg ){
        rabbitTemplate.convertAndSend(MsgConfig.MANUAL_EXCHANGE_NAME,MsgConfig.MANUAL_ROUTING_KEY,msg);
        return "success";
    }

    @GetMapping("/send/{exchange}/{routingkey}/{msg}")
    public String sendMessage(@PathVariable("exchange") String exchange,@PathVariable("routingkey") String routingkey,@PathVariable("msg") String msg ){
        rabbitTemplate.convertAndSend(exchange,routingkey,msg);
        return "success";
    }
}

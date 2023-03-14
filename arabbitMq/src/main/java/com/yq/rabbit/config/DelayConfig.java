//package com.yq.rabbit.config;
//
//import org.springframework.amqp.core.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author yuqian
// * @ClassName DelayConfig
// * @description: 基于插件的延迟消息
// * @date 2023年03月13日
// */
//@Configuration
//public class DelayConfig {
//    public static final String DELAYED_QUEUE_NAME = "delayed.queue";
//    public static final String DELAYED_EXCHANGE_NAME = "delayed.exchange";
//    public static final String DELAYED_ROUTING_KEY = "delayed.routingkey";
//
//    // 队列
//    @Bean
//    public Queue delayQueue() {
//        return new Queue(DELAYED_QUEUE_NAME, true);
//    }
//
//    //交换机
//    @Bean
//    public CustomExchange delayExchange() {
//        Map<String,Object> args = new HashMap<>();
//        args.put("x-delayed-type", "direct");
//        return new CustomExchange(DELAYED_EXCHANGE_NAME,"x-delay-message",true,false,args);
//    }
//    //绑定
//    @Bean
//    public Binding bindingExchange(Queue delayQueue, Exchange delayExchange){
//        return BindingBuilder.bind(delayQueue).to(delayExchange).with(DELAYED_ROUTING_KEY).noargs();
//    }
//}

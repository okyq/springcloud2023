//package com.yq.rabbit.consumer;
//
//import com.yq.rabbit.config.DelayConfig;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
///**
// * @author yuqian
// * @ClassName DelayConsumer
// * @description:
// * @date 2023年03月13日
// */
//@Component
//@Slf4j
//public class DelayConsumer {
//
//    @RabbitListener(queues = DelayConfig.DELAYED_QUEUE_NAME)
//    public void reciveMsg(Message msg){
//        String message = new String(msg.getBody());
//        log.info("当前时间：{},收到延迟队列信息{}", new Date().toString(), message);
//    }
//}

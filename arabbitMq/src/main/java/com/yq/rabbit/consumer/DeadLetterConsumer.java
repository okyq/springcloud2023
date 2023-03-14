package com.yq.rabbit.consumer;

import com.rabbitmq.client.AMQP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

/**
 * @author yuqian
 * @ClassName DeadLetterConsumer
 * @description:
 * @date 2023年03月12日
 */
@Component
@Slf4j
public class DeadLetterConsumer {
    @RabbitListener(queues = "QD")
    public void receiveD(Message message) {
        String msg = new String(message.getBody());
        log.info("当前时间：{},收到死信队列信息{}", new Date(), msg);
    }
}

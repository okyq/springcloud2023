package com.yq.rabbit.consumer;

import com.yq.rabbit.config.PriorityConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Time;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author yuqian
 * @ClassName PriorityConsumer
 * @description:
 * @date 2023年03月14日
 */
@Component
@Slf4j
public class PriorityConsumer {

    @RabbitListener(queues = PriorityConfig.PRIORITY_QUEUE_NAME)
    public void recive(Message message) throws InterruptedException {
        String msg = new String(message.getBody());
        log.info("优先级消费者消费了消息：{}", msg);
    }
}

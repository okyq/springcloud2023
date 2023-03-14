package com.yq.rabbit.consumer;

import com.yq.rabbit.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author yuqian
 * @ClassName WarnConsumer
 * @description: 报警消费者
 * @date 2023年03月14日
 */
@Component
@Slf4j
public class WarnConsumer {
    @RabbitListener(queues = ConfirmConfig.WARN_QUEUE_NAME)
    public void reviceConfirm(Message message){
        String msg = new String(message.getBody());
        log.error("有消息进入了报警队列：接受到的消息为：{}", msg);
    }
}

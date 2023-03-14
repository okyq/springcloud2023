package com.yq.rabbit.consumer;

import com.yq.rabbit.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author yuqian
 * @ClassName ConfirmConsumer
 * @description:
 * @date 2023年03月14日
 */
@Component
@Slf4j
public class ConfirmConsumer {
    @RabbitListener(queues = ConfirmConfig.CONFIRM_QUEUE_NAME)
    public void reviceConfirm(Message message){
        String msg = new String(message.getBody());
        log.info("接受到的消息为：{}", msg);
    }
}

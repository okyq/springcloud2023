package com.yq.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import com.yq.rabbitmq.config.MsgConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author yuqian
 * @ClassName ManualConsumer
 * @description:
 * @date 2023年03月14日
 */

@Component
@Slf4j
public class ManualConsumer {

    @RabbitListener(queues = MsgConfig.MANUAL_QUEUE_NAME)
    public void consumerDoAck(Message message, Channel channel) throws IOException {
        log.info("收到了消息，开始准备手动应答：{}", message);
        String msg = new String(message.getBody());
        if (msg.contains("success")) {
            // RabbitMQ的ack机制中，第二个参数返回true，表示需要将这条消息投递给其他的消费者重新消费
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            log.info("手动应答消费");
        } else {
            // 第三个参数true，表示这个消息会重新进入队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            log.info("手动应答否定");
        }
    }

}

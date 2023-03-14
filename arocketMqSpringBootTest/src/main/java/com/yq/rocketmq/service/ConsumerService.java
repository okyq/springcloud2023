package com.yq.rocketmq.service;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Service;

/**
 * @author yuqian
 * @ClassName ConsumerService
 * @description:
 * @date 2023年03月08日
 */
@Service
@RocketMQMessageListener(consumerGroup = "group10", topic = "topic12", selectorType = SelectorType.SQL92, selectorExpression = "TAGS='tags1'")
public class ConsumerService implements RocketMQListener<String>, RocketMQPushConsumerLifecycleListener {
    @Override
    public void onMessage(String o) {
        System.out.println("接收到了消息");
        System.out.println(o.toString());
        System.out.println("消息处理完毕");
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {
        defaultMQPushConsumer.setNamesrvAddr("192.168.2.230:9876");
    }
}

package com.yq.springcloud;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author yuqian
 * @ClassName Consumer
 * @description:
 * @date 2023年03月06日
 */
public class Consumer {
    public static void main(String[] args) throws Exception {
//        1，谁来收？
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group1");

//        2,从哪里收？
        consumer.setNamesrvAddr("192.168.2.230:9876");
        consumer.subscribe("topic1","*");

//        3,怎么接受？push模型需要监听器，接受了要干嘛？
        consumer.registerMessageListener((MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> {
            //业务逻辑
            for(MessageExt ext : list){
                System.out.println("消息为："+new String(ext.getBody()));
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        consumer.start();
        System.out.println("消费者已经启动");
        //消费者有监听器，所以需要一直启动来消费消息
    }
}

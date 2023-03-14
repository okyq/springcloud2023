package com.yq.springcloud.one2many;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * @author yuqian
 * @ClassName Producer
 * @description: 生产者
 * @date 2023年03月06日
 */
public class Producer1 {
    public static void main(String[] args) throws Exception {
//        1，谁来发；
        DefaultMQProducer producer = new DefaultMQProducer("group1");

//        2，发给谁；
        producer.setNamesrvAddr("192.168.2.230:9876");
        producer.start();

        for (int i = 0; i < 10; i++) {
//            3，发什么；
            String msg = "hello world"+i;
            Message message = new Message("topic4","tags1",msg.getBytes());
//            4，发送；
            SendResult sendResult = producer.send(message);
//        5，发的结果是什么；
            System.out.println(sendResult);
        }

//        6，打扫战场；
        producer.shutdown();

    }
}

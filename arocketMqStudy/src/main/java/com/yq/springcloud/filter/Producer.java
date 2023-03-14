package com.yq.springcloud.filter;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuqian
 * @ClassName Producer
 * @description: 生产者
 * @date 2023年03月06日
 */
public class Producer {
    public static void main(String[] args) throws Exception {
//        1，谁来发；
        DefaultMQProducer producer = new DefaultMQProducer("group1");

//        2，发给谁；
        producer.setNamesrvAddr("192.168.2.230:9876");
        producer.start();

        List<Message> messageList = new ArrayList<>();

//        3，发什么；
        String msg = "hello world zhangsan 18";
        Message message = new Message("topic9","tags2",msg.getBytes());

        //自定义用户属性
        message.putUserProperty("name","zhangsan");
        message.putUserProperty("age","18");
        messageList.add(message);

        String msg1 = "hello world zhangsan 28";
        Message message1 = new Message("topic9","tags2",msg1.getBytes());

        //自定义用户属性
        message1.putUserProperty("name","zhangsan");
        message1.putUserProperty("age","28");
        messageList.add(message1);

//        4，发送；
        SendResult sendResult = producer.send(messageList);

//        5，发的结果是什么；
        System.out.println(sendResult);

//        6，打扫战场；
        producer.shutdown();

    }
}

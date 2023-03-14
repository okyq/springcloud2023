package com.yq.springcloud.typeOfMessage;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuqian
 * @ClassName ProducerBatch
 * @description:
 * @date 2023年03月07日
 */
public class ProducerBatch {
    public static void main(String[] args) throws Exception {
//        1，谁来发；
        DefaultMQProducer producer = new DefaultMQProducer("group1");

//        2，发给谁；
        producer.setNamesrvAddr("192.168.2.230:9876");
        producer.start();

        List<Message> messageList = new ArrayList<>();
        //批量发送消息
        for (int i = 0; i < 10; i++) {
            String msg = "hello world"+i;
            Message message = new Message("topic6","tags1",msg.getBytes());
            messageList.add(message);
        }
        SendResult send = producer.send(messageList);
        System.out.println(send);

//        6，打扫战场；
        producer.shutdown();

    }
}

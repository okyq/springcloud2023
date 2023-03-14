package com.yq.springcloud.typeOfMessage;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
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

        //异步发送消息
        for (int i = 0; i < 10; i++) {
            String msg = "hello world"+i;
            Message message = new Message("topic5","tags1",msg.getBytes());
            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("消息发送成功"+sendResult);
                    System.out.println("==================");
                }

                @Override
                public void onException(Throwable throwable) {
                    System.out.println("消息发送失败"+throwable);
                    System.out.println("===============");
                }
            });
            System.out.println("----------------异步消息发送完成---------------");
        }

//        6，打扫战场；
        //producer.shutdown();

    }
}

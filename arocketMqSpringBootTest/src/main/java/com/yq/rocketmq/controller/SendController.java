package com.yq.rocketmq.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuqian
 * @ClassName SendController
 * @description:
 * @date 2023年03月08日
 */
@RestController
@Slf4j
public class SendController {

    @Autowired
    RocketMQTemplate rocketMQTemplate;//建立连接以及断开连接

    @GetMapping("/send")
    public String send() {
        String msg = "hello from springboot";
        //通过send方法发送，需要一个messageBuilder,send方法默认发送同步消息
        rocketMQTemplate.send("topic10:tags1", MessageBuilder.withPayload(msg).build());

        //同步消息：
        rocketMQTemplate.syncSend("topic10:tags1", "这是一条同步消息");

        //异步消息+延迟：
        rocketMQTemplate.asyncSend("topic10:tags1", MessageBuilder.withPayload("这是一条异步消息").build(), new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("异步消息发送成功");
            }

            @Override
            public void onException(Throwable throwable) {
                System.out.println("异步消息发送失败");
            }
        },2000,1);

        //单向消息:
        rocketMQTemplate.sendOneWay("topic10:tags1","这是单向消息");


        //转换和发送
        rocketMQTemplate.convertAndSend("topic10:tags1","这是单向消息");

        //发送带属性的消息
        rocketMQTemplate.syncSend("topic10:tags1","这是一条消息，name=zhangsan");
        rocketMQTemplate.syncSend("topic10:tags1",MessageBuilder.withPayload("这是一条消息，name=zhangsan")
                .setHeader("name","zhangsan")
                .build());


        List<Message> messageList = new ArrayList<>();
        //批量发送消息
        for (int i = 0; i < 10; i++) {
            String msg1 = "批量发送测试tag"+i;
            Message message = new Message("topic10","tags1",msg1.getBytes());
            messageList.add(message);
        }
        rocketMQTemplate.syncSend("topic11:tags2", messageList,100);

        //批量发送消息
        for (int i = 0; i < 20; i++) {
            String msg1 = "顺序消息-------种类："+i%4+"，数量："+i;
            rocketMQTemplate.syncSendOrderly("topic11:tags1", MessageBuilder.withPayload(msg1).build(), i%4+"");
        }

        return "success";
    }

    @GetMapping("/sendTsc/{id}")
    public String Transaction(@PathVariable String id){
        log.info("---------------消息开始发送-------入参："+id);
        TransactionSendResult transactionSendResult = rocketMQTemplate.sendMessageInTransaction("myGroup1",
                "topic12:tags1",
                MessageBuilder.withPayload("这是一条事务消息").build(),
                id);
        log.info("---------------消息发送状态："+transactionSendResult);
        return "transactionSendResult";
    }
}

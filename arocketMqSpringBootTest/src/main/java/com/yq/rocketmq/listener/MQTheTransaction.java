package com.yq.rocketmq.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

/**
 * @author yuqian
 * @ClassName MQTheTransaction
 * @description:
 * @date 2023年03月08日
 */
@Slf4j
@RocketMQTransactionListener(txProducerGroup = "myGroup1")
public class MQTheTransaction implements RocketMQLocalTransactionListener {
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        log.info("----------开始本地事务--------执行方法，入参："+o.toString());
        // 执行本地事务
        RocketMQLocalTransactionState result = RocketMQLocalTransactionState.COMMIT;
        //模拟消息unknow
        if(Integer.parseInt(o.toString())==0){
            log.info("---------本地事务执行unknow,需要执行检查事务---------");
            result = RocketMQLocalTransactionState.UNKNOWN;
        } else if (Integer.parseInt(o.toString())>0) {
            log.info("---------本地事务执行成功----------");
            result = RocketMQLocalTransactionState.COMMIT;
        } else {
            log.info("-----------本地事务执行失败------------");
            result = RocketMQLocalTransactionState.ROLLBACK;
        }
        return result;
    }

    @Override //检查本地事务的方法，返回三种状态：COMMIT、ROLLBACK、UNKNOWN。当状态为UNKNOWN时，该方法会被重复调用直到返回COMMIT或ROLLBACK为止。
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        log.info("----------执行检查任务-------------");
        return RocketMQLocalTransactionState.COMMIT;
    }
}

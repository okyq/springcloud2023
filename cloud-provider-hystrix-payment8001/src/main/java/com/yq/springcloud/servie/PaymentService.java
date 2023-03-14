package com.yq.springcloud.servie;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author yuqian
 * @ClassName PaymentService
 * @description: 这里节约时间，只写了service
 * @date 2023年02月27日
 */
@Service
public class PaymentService {

    /**
      * @description: 正常的方法
      * @author yuqian
      * @params [id]
      * @date 2023/2/27
      * @return java.lang.String
      */
    public String paymentInfoOK(Integer id)
    {
        return "线程池:"+Thread.currentThread().getName()+"paymentInfo_OK,id: "+id+"\t"+"O(∩_∩)O";
    }

    /**
      * @description:超时的方法，演示降级
      * @author yuqian
      * @params [id]
      * @date 2023/2/27
      * @return java.lang.String
      */
    @HystrixCommand(fallbackMethod = "paymentInfoTimeOutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="3000")
    })
    public String paymentInfoTimeOut(Integer id)
    {
        // int x = 10/0;
        // 上面的会直接进入报错
        int time = 5;
        try { TimeUnit.SECONDS.sleep(time); } catch (InterruptedException e) { e.printStackTrace(); }
        return "线程池:"+Thread.currentThread().getName()+"paymentInfo_TimeOut,id: "+id+"\t"+"O(∩_∩)O，耗费:"+time+"秒";
    }

    /**
      * @description: 超时fallback方法
      * @author yuqian
      * @params [id]
      * @date 2023/2/27
      * @return java.lang.String
      */
    public String paymentInfoTimeOutHandler(Integer id){
        return "/(ㄒoㄒ)/调用支付接口8001超时或异常：\t"+ "\t当前线程池名字" + Thread.currentThread().getName()+"/(ㄒoㄒ)/~~";
    }

    //============================服务熔断====================
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//失败率%
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            //以上配置的意思是：开启断路器，在十秒中的时间里，如果十次请求有六次失败了就跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id)
    {
        if(id < 0)
        {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功，流水号: " + serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id)
    {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " +id;
    }
}

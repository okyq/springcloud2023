package com.yq.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yq.springcloud.service.HystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuqian
 * @ClassName HystrixController
 * @description:
 * @date 2023年02月27日
 */
@RestController
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class HystrixController {

    @Autowired
    private HystrixService hystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id)
    {
        String result = hystrixService.paymentInfoOK(id);
        return result;
    }

    /**
      * @description: 1.5s超时报错，使用自定义fallback方法
      * @author yuqian
      * @params [id]
      * @date 2023/2/27
      * @return java.lang.String
      */
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1500")
    })
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) throws InterruptedException
    {
        String result = hystrixService.paymentInfoTimeOut(id);
        return result;
    }

    /**
      * @description: 直接报错,使用默认fallbakc方法
      * @author yuqian
      * @params [id]
      * @date 2023/2/27
      * @return java.lang.String
      */
    @GetMapping("/consumer/payment/hystrix/timeout/default/{id}")
    @HystrixCommand
    public String paymentInfo_TimeOut_default(@PathVariable("id") Integer id) throws InterruptedException
    {
        int x = 1/0;
        String result = hystrixService.paymentInfoTimeOut(id);
        return result;
    }

    /**
      * @description: 自定义的异常处理方法
      * @author yuqian
      * @params [id]
      * @date 2023/2/27
      * @return java.lang.String
      */
    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id)
    {
        return "我是消费者80,对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
    }
    /**
      * @description: 全局异常处理方法
      * @author yuqian
      * @params []
      * @date 2023/2/27
      * @return java.lang.String
      */
    public String payment_Global_FallbackMethod()
    {
        return "Global异常处理信息，请稍后再试，/(ㄒoㄒ)/~~";
    }
}

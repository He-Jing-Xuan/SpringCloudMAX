package com.he.springcloud.controller;

import com.he.springcloud.service.PaymentHystrixService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
//全局兜底
//@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod") //添加了这个
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;
//    @Autowired
//    OrderService orderService;

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        System.out.println("hello1111");
        // int age = 10/0;
        //tring result = orderService.paymentInfo_Timeout(id);
       String result = paymentHystrixService.paymentInfo_TimeOut(id);
        return result;//result;
    }
    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id) {
        return "我是消费者80,对方支付系统繁忙请10秒钟后再试 或者 自己运行出错请检查自己,o(╥﹏╥)o";
    }
    // 下面是全局fallback方法
    public String payment_Global_FallbackMethod(){
        return "Global异常处理信息，请稍后再试，/(ㄒoㄒ)/~~";
    }
}

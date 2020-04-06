package com.qbk.mq.amqp.demo.consumer;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
/**
 * 另一种写法 ，无需在config里面配置
 * 直接使用RabbitListener注解声明队列、交换机、绑定
 */
@Component
@RabbitListener(
        bindings = @QueueBinding(
                value = @Queue("myDirectQueue"),
                exchange = @Exchange(value = "myDirectExchange", type = ExchangeTypes.DIRECT),
                key = "mine.direct"
        ))
public class MyDirectListener {
    /**
     * listenerAdapter
     *
     * @param msg 消息内容,当只有一个参数的时候可以不加@Payload注解
     */
    @RabbitHandler
    public void onMessage(@Payload String msg) {
        System.out.println(msg);
    }
}
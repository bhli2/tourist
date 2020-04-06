package com.qbk.mq.amqp.demo.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
/**
 * 一个@RabbitListener就对应一个MessageListenerContainer
 * ConnectionFactory被自动注入，队列名称可以用queues，queuesToDeclare，或是bindings里面的queue指定，
 * 收到消息的处理方法用@RabbitHandler指定,或是直接把@RabbitListener打在对应的方法上就行.
 */
@Component
@RabbitListener(queues = "FIRST_QUEUE")
public class FirstConsumer {
    /**
     * @param msg 消息内容,当只有一个参数的时候可以不加@Payload注解
     */
    @RabbitHandler
    public void process(String msg){
        System.out.println(" first queue received msg : " + msg);
    }
}

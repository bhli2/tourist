package com.qbk.mq.amqp.demo.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 队列和交换机的绑定都在RabbitConfig里
 */
@Component
@RabbitListener(queues = "THIRD_QUEUE")
public class ThirdConsumer {
    @RabbitHandler
    public void process(String msg){
        System.out.println(" third queue received msg : " + msg);
    }
}

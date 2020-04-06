package com.qbk.mq.amqp.demo.provider;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyProvider {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(){

        // Default 默认交换机,默认交换机其实就是直连交换机,可以理解为名称为空字符串的直连交换机,
        // 一个queue若不指定binding的交换机,就被绑定到默认交换机上,routingKey为queue的名称
        amqpTemplate.convertAndSend("","FIRST_QUEUE","-------- a direct msg");

        /**
         * @param exchange 交换机的名字
         * @param routingKey  路由key
         * @param message 发送的消息
         */
        amqpTemplate.convertAndSend("TOPIC_EXCHANGE","shanghai.qbk.travel","-------- a topic msg : shanghai.qbk.消息");
        amqpTemplate.convertAndSend("TOPIC_EXCHANGE","beijing.qbk.work","-------- a topic msg : beijing.qbk.消息");

        amqpTemplate.convertAndSend("FANOUT_EXCHANGE","","-------- a fanout msg");

        amqpTemplate.convertAndSend("myDirectExchange","mine.direct","this is a direct message");
    }

}

package com.qbk.satemachine.multi.controller;

import com.qbk.satemachine.multi.config.OrderStateMachineBuilder;
import com.qbk.satemachine.multi.enums.OrderEvents;
import com.qbk.satemachine.multi.enums.OrderStates;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderStateMachineBuilder orderStateMachineBuilder;

    @Autowired
    private BeanFactory beanFactory;

    /**
     *  次请求testOrderState就会生成一个新的statemachine
     *
     */
    @RequestMapping("/testOrderState")
    public void testOrderState() throws Exception {

        StateMachine<OrderStates, OrderEvents> stateMachine = orderStateMachineBuilder.build(beanFactory);

        // 创建流程
        stateMachine.start();

        // 触发PAY事件
        stateMachine.sendEvent(OrderEvents.PAY);

        // 触发RECEIVE事件
        stateMachine.sendEvent(OrderEvents.RECEIVE);

        // 获取最终状态
        System.out.println("最终状态：" + stateMachine.getState().getId());
    }
}

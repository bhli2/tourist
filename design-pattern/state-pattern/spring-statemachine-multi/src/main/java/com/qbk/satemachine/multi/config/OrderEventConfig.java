package com.qbk.satemachine.multi.config;

import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * 注解监听器
 *
 * 这个id对应的就是OrderStateMachineBuilder里面的MACHINEID，被builder写到.machineId(MACHINEID)里面。
 */
@WithStateMachine(id="orderMachine")
public class OrderEventConfig {

    @OnTransition(target = "UNPAID")
    public void create() {
        System.out.println("-------订单创建，待支付");
    }

    @OnTransition(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
    public void pay() {
        System.out.println("---------用户完成支付，待收货");
    }

    @OnTransition(source = "WAITING_FOR_RECEIVE", target = "DONE")
    public void receive() {
        System.out.println("---------用户已收货，订单完成");
    }

}

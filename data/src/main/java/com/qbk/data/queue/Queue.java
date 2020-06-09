package com.qbk.data.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Queue {

    private volatile Object[] data;

    private volatile int front;

    private volatile int rear;

    private static ResourceClass resourceClass = new ResourceClass();

    private volatile long consumerTime;

    public Queue(int size) {
        if(size < 0){
            throw new IllegalArgumentException("size can't lt 0 !");
        }
        this.data = new Object[size];
    }

    public Queue(int size,long consumerTime) {
        if(size < 0){
            throw new IllegalArgumentException("size can't lt 0 !");
        }
        this.data = new Object[size];
        this.consumerTime = consumerTime;
    }

    public void put(Object e){
        resourceClass.lock.lock();
        try{
            if((this.rear + 1) % this.data.length == this.front){
                //TODO wait
                resourceClass.condition.await();
                Thread.sleep(consumerTime);
            }
            this.data[this.rear] = e;
            this.rear = (this.rear + 1) % this.data.length;
            //TODO notifyAll
            resourceClass.condition.signalAll();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            resourceClass.lock.unlock();
        }

    }

    public Object pop(){
        resourceClass.lock.lock();
        Object obj = null;
        try{
            if(this.front == this.rear){
                //TODO wait
                resourceClass.condition.await();
                Thread.sleep(consumerTime);
            }
            obj = data[this.front];
            this.front = (this.front + 1) % this.data.length;
            //TODO notifyAll
            resourceClass.condition.signalAll();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            resourceClass.lock.unlock();
        }
        return obj;
    }

    public static void main(String[] args) {
        Queue queue = new Queue(10,2000);
        new Thread(() -> {
            for(int i = 0 ; i < 100 ; i ++){
                queue.put(i);
            }
        },"PUT").start();

        new Thread(() -> {
            for(int i = 0 ; i < 100 ; i ++){
                Object pop = queue.pop();
                System.out.println(pop);
            }
        },"POP").start();
    }

}

class ResourceClass{

    protected Lock lock = new ReentrantLock();

    protected Condition condition = lock.newCondition();

}

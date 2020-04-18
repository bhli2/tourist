package com.qbk.bean.aware;

import com.qbk.bean.service.MyService;
import com.qbk.bean.service.impl.MyServiceImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import 循环依赖.三个循环依赖.C1;

import javax.annotation.PostConstruct;

/**
 * 获取ApplicationContext容器
 */
@Component
public class MyApplicationContextAware implements ApplicationContextAware {

    private static ApplicationContext context ;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext ;
        System.out.println("获取applicationContext成功");
    }

    public static ApplicationContext getApplicationContext(){
        return context;
    }

    @Autowired
    private MyService myService;

    @PostConstruct
    public void init(){
        System.out.println(myService);

        MyService myService = (MyService) context.getBean("myServiceImpl");
        /*
        getBean 实际调用的是 DefaultSingletonBeanRegistry 中的三个缓存
        private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);
        private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>(16);
        private final Map<String, Object> earlySingletonObjects = new HashMap<>(16);
         */
        myService.fun();
    }
}
package com.chris.cloud;

import com.chris.cloud.bean.processor.service.IHelloService;
import com.chris.cloud.bean.processor.service.impl.HelloServiceImpl1;
import com.chris.cloud.bean.proxy.AroundInteceptor;
import com.chris.cloud.bean.proxy.MyTarget;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

public class ProxyFactoryTest {
    @Test
    public void classProxy() {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new MyTarget());
        proxyFactory.addAdvice(new AroundInteceptor());
        MyTarget targetProxy = (MyTarget) proxyFactory.getProxy();
        targetProxy.printName();
        System.out.println(targetProxy.getClass().getName());
    }

    @Test
    public void interfaceProxy() {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setInterfaces(IHelloService.class);
        proxyFactory.addAdvice(new AroundInteceptor());
        proxyFactory.setTarget(new HelloServiceImpl1());
        IHelloService targetProxy = (IHelloService) proxyFactory.getProxy();
        targetProxy.sayHello();
        System.out.println(targetProxy.getClass().getName());
    }
}

package com.chris.cloud.bean.processor.service;

import org.springframework.beans.factory.InitializingBean;

public interface IHelloService extends InitializingBean {
    void sayHello();

    void initName();
}

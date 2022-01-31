package com.chris.cloud.bean.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Map;

@Service
@Slf4j
public class HelloServiceInjectProcessor implements BeanPostProcessor {
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("begin postProcessBeforeInitialization, bean:{}, name:{}", bean.toString(), beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("begin postProcessAfterInitialization, bean:{}, name:{}", bean.toString(), beanName);

        Class<?> clz = bean.getClass();
        Field[] declaredFields = clz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(RoutingInjected.class)) {
                if (!field.getType().isInterface()) {
                    throw new BeanCreationException("RountingInjected field:{} must bea declared as an interface" +
                            "@Class:{}", field.getName(), clz.getSimpleName());
                }

                try {
                    handleRoutingInjected(field, bean, field.getType());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return bean;
    }

    private void handleRoutingInjected(Field field, Object bean, Class<?> type) throws IllegalAccessException {
        Map<String, ?> candidates = applicationContext.getBeansOfType(type);
        field.setAccessible(true);
        if (candidates.size() == 1) {
            field.set(bean, candidates.values().iterator().next());
        } else if (candidates.size() == 2) {
            String injectVal = field.getAnnotation(RoutingInjected.class).value();
            Object proxy = RoutingBeanProxyFactroy.createProxy(injectVal, type, candidates);
            field.set(bean, proxy);
        }
    }
}

package com.chris.cloud.bean.processor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;

import java.util.Map;
import java.util.Objects;

public class RoutingBeanProxyFactroy {
    private final static String DEFAULT_BEAN_NAME = "helloServiceImpl1";

    public static Object createProxy(String name, Class<?> type, Map<String, ?> candidates) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setInterfaces(type);
        proxyFactory.addAdvice(new VersionRoutingMethodInterceptor(name, candidates));
        return proxyFactory.getProxy();
    }

    static class VersionRoutingMethodInterceptor implements MethodInterceptor {

        private Object targetObject;

        public VersionRoutingMethodInterceptor(String name, Map<String, ?> beans) {
            this.targetObject = beans.get(name);
            if (Objects.isNull(this.targetObject)) {
                this.targetObject = beans.get(DEFAULT_BEAN_NAME);
            }
        }

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            return invocation.getMethod().invoke(this.targetObject, invocation.getArguments());
        }
    }
}

package com.chris.cloud.bean.proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class AroundInteceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println(invocation.getMethod().getName() + " before invoke");
        Object proceed = invocation.proceed();
        System.out.println(invocation.getMethod().getName() + " after invoke");
        return proceed;
    }
}

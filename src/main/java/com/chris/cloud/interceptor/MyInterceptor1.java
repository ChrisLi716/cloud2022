package com.chris.cloud.interceptor;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class MyInterceptor1 implements HandlerInterceptor {

    /**
     * 前置处理，可以中断请求继续
     * true  请求继续
     * false 中断请求
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("MyInterceptor1 preHandle: begin myInterceptor");
        return true;
    }

    /**
     * 后置处理，即afterReturning通知
     * 在controller执行之后，能过拦截器执行一段代码，此时只是controller很乖完毕，视图还没有渲染
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        log.info("MyInterceptor1 postHandle: controller has executed");

    }

    /**
     * 整个请求结束后执行
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        log.info("MyInterceptor1 afterCompletion: request done");
    }
}

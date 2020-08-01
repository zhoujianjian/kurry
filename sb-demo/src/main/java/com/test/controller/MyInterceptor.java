package com.test.controller;

import org.springframework.web.servlet.HandlerInterceptor;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: MyInterceptor
 * @Description: 描述
 * @Author: zhoujian
 * @Date: 2020/7/24$ 13:50$
 * @Version: 1.0
 */
public class MyInterceptor implements HandlerInterceptor {

    public static final Logger log = LoggerFactory.getLogger(MyInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle");
        //如果是SpringMVC请求
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            log.info("当前拦截的方法为：{}", handlerMethod.getMethod().getName());
            log.info("当前拦截的方法参数长度为：{}", handlerMethod.getMethod().getParameters().length);
            log.info("当前拦截的类为：{}", handlerMethod.getBean().getClass().getName());
            String uri = request.getRequestURI();
            log.info("拦截的uri：" + uri);
            //获取方法注解

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        log.info("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        log.info("afterCompletion");
    }
}

package com.cancer.star.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.cancer.commons.api.APIResponse;
import com.cancer.commons.api.enums.ApiResponseEnum;
import com.cancer.commons.exception.BizException;
import com.cancer.commons.utils.GsonUtils;
import com.cancer.commons.utils.ObjectUtil;
import com.cancer.commons.utils.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author:jian.zhou
 * @DATE: 2017/10/16.
 * @Version:0.0.1
 * @Desc:  校验参数有效性 记录请求参数, 返回数值.
 */
@Aspect
@Component
@Slf4j
public class ParameterInvalidInterceptor {



    @Pointcut("execution(* com.cancer..http..*Controller.*(..))")
    public void controller() {
    }

    @Around(value = "controller()")
    public Object profile(ProceedingJoinPoint poj) throws Throwable {
        Long start = new Date().getTime();
        try {
            Object result = poj.proceed(); //处理业务流程
            log(poj.getSignature(), poj.getArgs(), result, false); //打印结果
            log.warn("[ execute time: {}ms ] ", System.currentTimeMillis() - start );
            return result;
        } catch (Exception e) {
            log(poj.getSignature(), poj.getArgs(), e.getMessage(), false); //打印结果
            throw e;
        }

    }

    private void log(final Signature signature, Object[] args, final Object result, boolean isService) {
        try {
            Object[] tmpArgs = new Object[args.length];
            int i = 0;
            for (Object o : args) {
                if (o instanceof HttpServletRequest
                        || o instanceof HttpServletResponse
                        || o instanceof BindingResult) {
                    continue;
                }
                tmpArgs[i++] = o;
            }
            String uri = null;
            if (!isService) {
                HttpServletRequest httpServletRequest = ServletUtils.getHttpServletRequest();
                uri = httpServletRequest.getRequestURI();
                log.warn("[requestIP     ] : {}", ServletUtils.getClientIpAddress(httpServletRequest));
                log.warn("[requestURI    ] : {}", uri);
//                logger.warn("[requestParam  ] : {}", GsonUtils.toJSonStr(httpServletRequest.getParameterMap()));
            }
            log.warn("[callMethod    ] : {}", signature.toString());
            log.warn("[requestParam           ] : {}", GsonUtils.toJSonStr(tmpArgs));
            log.warn("[result          ] : {}", JSONArray.toJSONString(result));
        } catch (Exception e) {
            log.error("log exceptioon {}", e);
        }
    }

}

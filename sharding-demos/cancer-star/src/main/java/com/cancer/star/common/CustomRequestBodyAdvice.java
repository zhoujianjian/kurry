package com.cancer.star.common;


import com.cancer.commons.encrypt.Coder;
import com.cancer.commons.encrypt.CustomCodec;
import com.cancer.commons.encrypt.RSAKeycrypt;
import com.cancer.commons.utils.ObjectUtil;
import com.cancer.commons.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

/**
 * 获取RequestBody参数, 解密
 * @author songzj
 * @date 16/4/16-19:53
 */
@ControllerAdvice
public class CustomRequestBodyAdvice implements RequestBodyAdvice {

    private static Logger logger = LoggerFactory.getLogger(CustomRequestBodyAdvice.class);

    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object handleEmptyBody(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return o;
    }

    @Override
    public Object afterBodyRead(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return o;
    }

    @Override
    public HttpInputMessage beforeBodyRead(final HttpInputMessage httpInputMessage, final MethodParameter methodParameter, final Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {

        return new HttpInputMessage() {
            @Override
            public InputStream getBody() throws IOException {

                    CustomCodec customCodec = methodParameter.getMethod().getAnnotation(CustomCodec.class);
                    if (customCodec != null && customCodec.encode()){
                        //需要服务端私钥解密
                        try {
                            String decode =  StringUtil.convertStreamToString(httpInputMessage.getBody());
                            byte[] decodeArr = Coder.decryptBASE64(decode);
                            byte[] decodeRSA = RSAKeycrypt.decryptByPrivateKey(decodeArr);
                            System.out.println(new String(decodeRSA, Coder.UTF_8));
                            return new ByteArrayInputStream(decodeRSA);
                        } catch (Exception e) {
                            logger.error(ObjectUtil.getFullStackTraceToString(e));
                        }
                    }
                 return httpInputMessage.getBody();
            }

            @Override
            public HttpHeaders getHeaders() {
                return httpInputMessage.getHeaders();
            }
        };
    }




}

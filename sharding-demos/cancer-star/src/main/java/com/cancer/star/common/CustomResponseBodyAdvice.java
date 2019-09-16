package com.cancer.star.common;



import com.cancer.commons.encrypt.CustomCodec;
import com.cancer.commons.utils.ObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ResponseBody 加密,属性过滤
 **/
@Order(1)
@ControllerAdvice(basePackages = {"com.cancer.star"})
public class CustomResponseBodyAdvice implements ResponseBodyAdvice {

    private static Logger logger = LoggerFactory.getLogger(CustomResponseBodyAdvice.class);

    private String[] includes;

    private String[] excludes;

    private boolean encode = false;

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return methodParameter.getMethod().isAnnotationPresent(CustomCodec.class);
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        includes = new String[]{};
        excludes = new String[]{};
        encode = false;

        if (o == null) {
            return null;
        }

        CustomCodec customCodec = methodParameter.getMethodAnnotation(CustomCodec.class);
        if (customCodec != null) {
            includes = customCodec.includes();
            excludes = customCodec.excludes();
            encode = customCodec.encode();
            Object retObj = null;
            if (o instanceof List) {
                List list = (List) o;
                retObj = handlerList(list);
            } else {
                retObj = handlerObject(o);
            }

            if (encode) {
//         FIXME 待修改
//       try {
//
//                    APIResponse apiResponse = (APIResponse) retObj;
//                    String jsonString = ObjectUtil.object2JsonString(apiResponse.getData());
//                    logger.debug(jsonString);
//                    byte[] bytes = RSAKeycrypt.encryptByPrivateKey(jsonString);
//                    String encryptString = Coder.encryptBASE64(bytes);
//
//                    logger.debug(jsonString);
//
//                    apiResponse.setData(encryptString);
//                    return apiResponse;
//                } catch (Exception e) {
//                    logger.error(ObjectUtil.getFullStackTraceToString(e));
//                }
            }

            return retObj;
        } else {
            return o;
        }
    }

    /**
     * 对对象进行处理
     *
     * @param o
     * @return
     */
    private Object handlerObject(Object o) {
        return ObjectUtil.excludeFieldToJson(o, includes, excludes);
    }


    /**
     * 对List 进行处理.
     *
     * @param list
     * @return
     */
    private Object handlerList(List list) {
        List retList = new ArrayList();
        for (Object o : list) {
            Map map = (Map) handlerObject(o);
            retList.add(map);
        }
        return retList;

    }
}

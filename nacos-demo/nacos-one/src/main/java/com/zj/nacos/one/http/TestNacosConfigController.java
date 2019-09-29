package com.zj.nacos.one.http;

import com.alibaba.nacos.api.config.annotation.NacosValue;

/**
 * @author: zhoujian
 * @Date: 2019/9/29 17:24
 * @Company: youanmi.
 * @Desc:
 */
public class TestNacosConfigController {

    @NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
    private boolean useLocalCache;

    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public boolean get() {
        return useLocalCache;
    }
}

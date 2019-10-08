package com.zj.grab.service.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.zj.grab.dto.CrawlerProductDto;
import com.zj.grab.service.GrabGoodService;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author: zhoujian
 * @Date: 2019/10/8 14:40
 * @Company: youanmi.
 * @Desc:
 */
public class TbGrabServiceImpl extends GrabGoodService {

    private static final String TKL_KEY = "sIbubHZTvW";

    @Override
    protected CrawlerProductDto grab(String code) throws IOException {
        return null;
    }

    @Override
    public String parseGetGoodId(String code) {
        String url = "https://api.taokouling.com/tkl/tkljm";
        HashMap<String,Object> map = new HashMap<>();
        map.put("tkl",code);
        map.put("apikey",TKL_KEY);
        String resp = HttpUtil.post(url,map);
        if ( StringUtils.isNotBlank(resp)){
            JSONObject jb =  JSONObject.parseObject(resp);
            String goodURL = jb.getString("url");
            if ( StringUtils.isNotBlank(goodURL)){
                String g = StringUtils.substring(goodURL,goodURL.indexOf(".com")+6,goodURL.indexOf(".htm"));
                if (g.equals("tem")){
                    return  StringUtils.substring(goodURL,goodURL.indexOf("&id")+4,goodURL.indexOf("&sourceType"));
                }
                return g;
            }
        }
        return null;
    }
}

package com.zj.grab.constants;

import com.google.common.collect.Maps;
import com.zj.grab.service.GrabGoodService;
import com.zj.grab.service.impl.BarCodeServiceImpl;
import com.zj.grab.service.impl.JdGrabServiceImpl;
import com.zj.grab.service.impl.PddGrabServiceImpl;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.Map;

/**
 * @author: zhoujian
 * @Date: 2019/7/5 10:08
 * @Company: youanmi.
 * @Desc:数据抓取类型
 */
public enum GrabGoodEnum {

    //依次：淘宝，天猫，京东，拼多多,条形码
    TB(1, "￥.*?￥"),
    TM(2, "￥.*?￥"),
    JD(3, "^http.*"),
    PDD(4, "^http.*"),
    TM_BATCH(5,""),
    BAR_CODE(6,"")
    ;


    private Integer type;

    /**
     * 正则表达式
     */
    private String pattern;


    GrabGoodEnum(Integer type, String pattern) {
        this.type = type;
        this.pattern = pattern;
    }


    private static Map<Integer, String> map;

    static {
        map = Maps.newHashMap();
        GrabGoodEnum[] values = GrabGoodEnum.values();
        for (GrabGoodEnum value : values) {
            map.put(value.type, value.pattern);
        }
        map = Collections.unmodifiableMap(map);
    }


    /**
     * 按类型 解析 口令
     *
     * @param type       //依次：淘宝，天猫，京东，拼多多
     * @param commandStr 口令
     * @return
     */
    public static String paresCommand(int type, String commandStr) {
        if (GrabGoodEnum.TB.type == type || GrabGoodEnum.TM.type == type) {
            return StringUtils.substring(commandStr, commandStr.indexOf("这段描述") + 4, commandStr.indexOf("后到"));
        } else {
            return commandStr;
        }
    }


    public static GrabGoodService getGrabGoodService( GrabGoodEnum grabGoodEnum ){
        int type = grabGoodEnum.getType();
        GrabGoodService grabGoodService = null;
        if (GrabGoodEnum.TB.type == type || GrabGoodEnum.TM.type == type) {

        } else if (GrabGoodEnum.JD.type == type  ){
            grabGoodService = new JdGrabServiceImpl();
        } else if (GrabGoodEnum.PDD.type == type  ){
            grabGoodService = new PddGrabServiceImpl();
        } else if (GrabGoodEnum.BAR_CODE.type == type  ){
            grabGoodService = new BarCodeServiceImpl();
        }
        return grabGoodService;
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }


    public static void main(String[] args) {
        String string = "https://item.m.jd.com/product/836075.html?wxa_abtest=b&utm_source=iosapp&utm_medium=appshare&utm_campaign=t_335139774&utm_term=Wxfriends&ad_od=share";
        System.out.println(GrabGoodEnum.paresCommand(3,string));
    }
}


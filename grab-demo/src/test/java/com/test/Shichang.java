package com.test;

import cn.hutool.core.util.ImageUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


import java.io.File;
import java.io.IOException;
import java.util.*;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import org.springframework.beans.factory.ListableBeanFactory;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author: zhoujian
 * @Date: 2019/10/10 10:46
 * @Company: youanmi.
 * @Desc:
 */
public class Shichang {

//
//    public static void main(String[] args) {
//
////      Double d1 =  7.88+5.00;
////        Double d2 =  12.88;
////        double d3 =  7.88d+5.00d;
////        double d4 =  12.88d;
////        System.out.println(d1.intValue()==d2.intValue());
////        System.out.println(d3==d4);
////        System.out.println(d3);
////        System.out.println(d4);
//
//
//
//
//    }



    public static void main(String[] args) throws Exception {
        String pattern = ".([€₤₳¢¤฿฿₵₡₫ƒ₲₭£₥₦₱〒₮₩₴₪៛﷼₢M₰₯₠₣₧ƒ][a−z0−9A−Z]9,11[€ ₤₳¢¤฿฿₵₡₫ƒ₲₭£₥₦₱〒₮₩₴₪៛﷼₢ℳ₰₯₠₣₧ƒ][a-z0-9A-Z]{9,11}[€₤₳¢¤฿฿₵₡₫ƒ₲₭£₥₦₱〒₮₩₴₪៛﷼₢M₰₯₠₣₧ƒ][a−z0−9A−Z]9,11[€₤₳¢¤฿฿₵₡₫ƒ₲₭£₥₦₱〒₮₩₴₪៛﷼₢ℳ₰₯₠₣₧ƒ]).";

        String code ="【萨米甲醛检测仪器家用便携式甲醛测试仪专业室内空气质量自量纸盒】，復zんíゞ这句话₴Qdz1Yx62EEz₴后咑閞\uD83D\uDC49綯℡寳\uD83D\uDC48\"}";



        String command =  StringUtils.substring(code, code.indexOf("这句话") + 3, code.indexOf("后"));

        System.out.println(command);


    }







    public static void getFuZhuang(String provinceCode)  {
        try {
            String defualt_url = "http://shichang.hznzcn.com/"+"/";
            Connection c1 = HttpConnection.connect(defualt_url);
            Document d1 = c1.get();
            System.out.println(d1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

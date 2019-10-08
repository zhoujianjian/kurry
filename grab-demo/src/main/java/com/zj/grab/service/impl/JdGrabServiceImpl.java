package com.zj.grab.service.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zj.grab.dto.CrawlerProductDto;
import com.zj.grab.dto.jd.JdProductPriceResp;
import com.zj.grab.service.GrabGoodService;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author: zhoujian
 * @Date: 2019/10/8 10:27
 * @Company: youanmi.
 * @Desc:通过京东分享网址爬取商品信息
 */
public class JdGrabServiceImpl extends GrabGoodService {



   final Pattern p = Pattern.compile("https.+?jpg");
    final   Pattern p1 = Pattern.compile("//img.+?jpg");
    final  Pattern pdata = Pattern.compile("data-lazyload=.+?jpg");

    public Document getJdDocument( String jdUrl ) throws Exception{
        Connection con = HttpConnection.connect(jdUrl);
        con.header("Host", "item.jd.com");
        return con.get();
    }

    public Document getRetryDocument(String jdUrl) {
        try {
            Retryer<Document> retryer = RetryerBuilder.<Document>newBuilder().retryIfException()
                    .withWaitStrategy(WaitStrategies.fixedWait(0, TimeUnit.MILLISECONDS))
                    .withStopStrategy(StopStrategies.stopAfterAttempt(5))
                    .build();
            return   retryer.call(() -> getJdDocument(jdUrl));
        } catch (Exception e) {
            return null;
        }
    }

    private List<String> getJdImgUrlByPattern(String content) {
        List<String> list = new ArrayList<>();
        if (content.indexOf("background-image:url(") > -1) {

            Matcher m = p.matcher(content);


            Matcher m1 = p1.matcher(content);

            while (m.find()) {
                list.add("https://" + content.substring(m.start() + 8, m.end()));
            }

            while (m1.find()) {
                list.add("https:" + content.substring(m1.start(), m1.end()));
            }
        }

        if (content.indexOf("data-lazyload=") > -1) {

            Matcher m = pdata.matcher(content);
            while (m.find()) {
                list.add(content.substring(m.start() + 17, m.end()));
            }
        }

        return list;
    }


    @Override
    public String parseGetGoodId(String code) {
        return code.substring(code.lastIndexOf("/") + 1, code.lastIndexOf(".html"));
    }


    @Override
    public CrawlerProductDto grab( String jdUrl ) {
        if ( jdUrl.contains("Wxfriends") || jdUrl.contains("CopyURL")){
            StringBuffer sb = new StringBuffer("https://item.jd.com");
            sb.append(StringUtils.substring(jdUrl,jdUrl.lastIndexOf("/"),jdUrl.indexOf("?")));
            jdUrl = sb.toString();
        }
        Document document = getRetryDocument(jdUrl);
        CrawlerProductDto result = new CrawlerProductDto();

        Elements selectSkuName = document.select("div.sku-name");
        if ( selectSkuName!=null && selectSkuName.size() > 0 ){
            String title = selectSkuName.get(0).text();
            result.setName(title);
        }
        String goodsIdStr = jdUrl.substring(jdUrl.lastIndexOf("/") + 1, jdUrl.lastIndexOf(".html"));
        result.setProductId(Long.parseLong(goodsIdStr));
        String pid = document.select("[class=parameter2 p-parameter-list] li:nth-child(2)").attr("title");

        JdProductPriceResp jdProductPrice = getJdProductPrice(pid);
        if( !Objects.isNull(jdProductPrice)){
            result.setPrice(jdProductPrice.getPrice());
            result.setOriginalPrice(jdProductPrice.getOriginPrice());
        }

        List<String> urls = new ArrayList<>();
        Elements imgUrls = document.getElementsByClass("lh");
        for (Element e : imgUrls) {
            if (e.children().size() > 0) {
                Elements eurls = e.children();
                eurls.forEach(f -> {
                    if (f.children().size() > 0) {
                        String src = f.children().get(0).attr("src");
                        if (StringUtils.isNotEmpty(src)) {
                            String temp = "";
                            if ( src.contains("s54x54_jfs") ){
                                temp = src.replace("s54x54_jfs","s450x450_jfs").substring(2);
                            }else if ( src.contains("n5")){
                                temp = src.replace("n5","n0").substring(2);
                            }
                            urls.add(temp);
                        }
                    }

                });
            }
        }
        result.setImageUrls(urls);
        Element script = document.selectFirst("script");

        String html = null;
        String html1 = script.html();
        if( StringUtils.isNotBlank(html1)){
            String[] split1 = html1.split("qualityLife:");
            if ( !Objects.isNull(split1) && split1.length > 1){
                String[] split2 = split1[1].split("desc: '//");
                if ( !Objects.isNull(split2) && split2.length > 1 ){
                    html = split2[1];
                }
            }

            if ( StringUtils.isNotBlank(html)){
                html = html.substring(0, html.indexOf(",") - 1);
            }
        }
        String body =  HttpUtil.get("https://"+html);
        if (body.substring(0,1).equals("(")){
            body = body.substring(body.indexOf("(")+1,body.lastIndexOf(")"));
        }
        JSONObject jsonObject = JSON.parseObject(body);
        String content = jsonObject.getString("content");
        List<String> detailUrls = getJdImgUrlByPattern(content);
        result.setProductDetailUrls(detailUrls);

        Elements skuEl = document.getElementsByAttribute("data-sku");
        Element skuOne = document.getElementById("choose-attr-1");
        Element skuTwo = document.getElementById("choose-attr-2");
        List<CrawlerProductDto.CproductSkuKv> skuKvOne = new ArrayList<>();
        List<CrawlerProductDto.CproductSkuKv> skuKvTwo = new ArrayList<>();
        List<CrawlerProductDto.CproductSkuMap> skuPrices = new ArrayList<>();
        if (skuTwo != null) {

            //二级sku
            Elements twoSkuEl = skuTwo.children().get(1).children();
            String twoSkuName = skuTwo.children().get(0).html();
            result.setSkuTwoName(twoSkuName);
            if(!Objects.isNull(skuOne) && !Objects.isNull(skuOne.children()) && !Objects.isNull(skuOne.children().get(0))){
                String oneName = skuOne.children().get(0).html();
                result.setSkuOneName(oneName);
            }
//            for (Element e : twoSkuEl) {
//
//                String twoskuId = e.attr("data-sku");
//                String twoskuName = e.attr("data-value");
//                skuKvTwo.add(new CrawlerProductDto.CproductSkuKv(twoskuId, twoskuName));
//                Connection conSku = getConnectionHeaderByIp("https://item.jd.com/" + twoskuId + ".html");
//                //getConnectionHeader("https://item.jd.com/" + twoskuId + ".html");
//                conSku.header("Host", "item.jd.com");
//                conSku.header("Referer", url);
//                conSku.timeout(200000000);
//                Document documentsku = conSku.get();
//                Element elelele = documentsku.getElementById("choose-attr-1");
//                if ( !Objects.isNull(elelele)&& !Objects.isNull(elelele.children()) && !Objects.isNull(elelele.children().get(1))){
//                    Elements children = elelele.children().get(1).children();
//                    if ( !CollectionUtils.isEmpty(children)){
//                        for (Element t : children) {
//                            String tid = t.attr("data-sku");
//                            String tname = t.attr("data-value");
//                            skuKvOne.add(new CrawlerProductDto.CproductSkuKv(tid, tname));
//                            JdProductPriceResp jdProductPriceResp = getJdProductPrice(tid);
//                            String price="0";
//                            if ( !Objects.isNull(jdProductPrice)){
//                                price =  jdProductPriceResp.getPrice();
//                            }
//                            skuPrices.add(new CrawlerProductDto.CproductSkuMap(tid + ";" + twoskuId, price));
//                        }
//                    }
//                }
//
//            }
            result.setSkuPrices(skuPrices);
            result.setSkuKvOne(skuKvOne);
            result.setSkuKvTwo(skuKvTwo);
        } else if (skuOne != null) {
            //一级sku
            String oneName = skuOne.children().get(0).html();
            result.setSkuOneName(oneName);
            for (Element e : skuEl) {
                String sku = e.attr("data-sku");
                String skuname = e.attr("data-value");
                if (StringUtils.isEmpty(skuname)) {
                    continue;
                }
                JdProductPriceResp jdProductPrice1 = getJdProductPrice(sku);
                if ( !Objects.isNull(jdProductPrice1)){
                    CrawlerProductDto.CproductSkuMap skuprice = new CrawlerProductDto.CproductSkuMap(sku, jdProductPrice1.getPrice());
                    skuPrices.add(skuprice);

                }
                skuKvOne.add(new CrawlerProductDto.CproductSkuKv(sku, skuname));
            }
            result.setSkuPrices(skuPrices);
            result.setSkuKvOne(skuKvOne);
        }
        return null;
    }



    /**
     * 获取京东商品价格
     *
     * @param sku
     * @return
     */
    private JdProductPriceResp getJdProductPrice(String sku) {
        JdProductPriceResp s = new JdProductPriceResp();
        if( StringUtils.isNotBlank(sku)){
            String priceUrlsku = "https://p.3.cn/prices/mgets?pduid=" + Math.random() + "&skuIds=J_" + sku;
            String body =  HttpUtil.get(priceUrlsku);
            if (body.equals("{\"error\":\"pdos_captcha\"}\n")){
                return null;
            }
            Gson gsonpk = new GsonBuilder().create();
            List<Map<String, String>> listpk = gsonpk.fromJson(body, List.class);
            s.setOriginPrice(listpk.get(0).get("op"));
            s.setPrice(listpk.get(0).get("p"));
        }
        return s;
    }






}

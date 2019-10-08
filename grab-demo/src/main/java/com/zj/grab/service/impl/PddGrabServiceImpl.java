package com.zj.grab.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import com.zj.grab.dto.CrawlerProductDto;
import com.zj.grab.service.GrabGoodService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author: zhoujian
 * @Date: 2019/10/8 11:16
 * @Company: youanmi.
 * @Desc:
 */
@Slf4j
public class PddGrabServiceImpl extends GrabGoodService {




    @Override
    public CrawlerProductDto grab(String url) throws IOException {
        try {
            Connection con = HttpConnection.connect(url);
            con.header("Host", "mobile.yangkeduo.com");
            Document document = con.get();
            Elements nameEl = document.getElementsByClass("g-n-goods-title");
            CrawlerProductDto result = new CrawlerProductDto();
            if ( !CollectionUtils.isEmpty(nameEl)){
                Element nameElChild = nameEl.get(0);
                String name = nameElChild.child(0).text();
                result.setName(name);
            }
            //result.setProductId(Long.parseLong(this.getGoodIdByType(4,url)));

            Elements scripts = document.select("script");
            if ( !Objects.isNull(scripts) && scripts.size() > 5  ){
                Element element ;
                if ( scripts.size() == 11){
                    element = scripts.get(5);
                } else {
                    element = scripts.get(6);
                }

                String[] splitScripts = element.html().split("store\":");
                if (  !Objects.isNull(splitScripts) && splitScripts.length > 1){
                    String scriptHtml = splitScripts[1];
                    scriptHtml = scriptHtml.substring(0, scriptHtml.length() - 2);
                    JSONObject jsonObject = JSON.parseObject(scriptHtml);
                    JSONObject goods = jsonObject.getJSONObject("initDataObj").getJSONObject("goods");
                    if ( Objects.isNull(goods)){
                         throw new IOException();
                    }
                    result.setName(goods.getString("goodsName"));
                    String top = goods.getString("topGallery")
                            .replace("[", "")
                            .replace("]", "")
                            .replaceAll("\\\"", "")
                            .replaceAll("//", "");
                    String[] topUtls = top.split(",");
                    result.setImageUrls(Arrays.asList(topUtls));
                    List<String> detailUrl = new ArrayList<>();
                    JSONArray detailarray = goods.getJSONArray("detailGallery");
                    detailarray.stream().forEach(e -> {
                        JSONObject a = (JSONObject) e;
                        detailUrl.add(a.getString("url").replace("//", ""));
                    });

                    result.setGoodsDesc(goods.getString("goodsDesc"));
                    result.setProductDetailUrls(detailUrl);

                    List<CrawlerProductDto.CproductSkuMap> skuPrices = new ArrayList<>();
                    List<CrawlerProductDto.CproductSkuKv> skuKvOne = new ArrayList<>();
                    List<CrawlerProductDto.CproductSkuKv> skuKvTwo = new ArrayList<>();
                    JSONArray skus = goods.getJSONArray("skus");
                    skus.stream().forEach(e -> {
                        JSONObject a = (JSONObject) e;
                        String groupPrice = a.getString("groupPrice");
                        if ( !groupPrice.contains(".")){
                            groupPrice = a.getString("groupPrice")+"00";
                        }
                        String normalPrice = a.getString("normalPrice");
                        if ( !normalPrice.contains(".")){
                            normalPrice = a.getString("normalPrice")+"00";
                        }
                        String quantityStr = a.getString("quantity");
                        Integer quantity = 0;
                        if ( !Objects.isNull(quantity)){
                            quantity = Integer.valueOf(quantityStr);
                        }
                        JSONArray specs = a.getJSONArray("specs");
                        JSONObject z = (JSONObject) specs.get(0);
                        skuKvOne.add(new CrawlerProductDto.CproductSkuKv(z.getString("spec_value_id"), z.getString("spec_value")));
                        if (StringUtils.isEmpty(result.getSkuOneName())) {
                            String spec_key = z.getString("spec_key");
                            result.setSkuOneName(spec_key);
                        }

                        result.setSkuSize(specs.size());
                        if (specs.size() > 1) {
                            JSONObject k = (JSONObject) specs.get(1);
                            if (StringUtils.isEmpty(result.getSkuTwoName())) {
                                String spec_key = k.getString("spec_key");
                                result.setSkuTwoName(spec_key);
                            }
                            skuKvTwo.add(new CrawlerProductDto.CproductSkuKv(k.getString("spec_value_id"), k.getString("spec_value")));
                            skuPrices.add(new CrawlerProductDto.CproductSkuMap(z.getString("spec_value_id") + ":" + k.getString("spec_value_id"), groupPrice,normalPrice,quantity));
                        } else {
                            skuPrices.add(new CrawlerProductDto.CproductSkuMap(z.getString("spec_value_id"), groupPrice,normalPrice,quantity));
                        }
                    });

                    result.setSkuKvOne(skuKvOne.stream().collect(
                            Collectors.collectingAndThen(
                                    Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(CrawlerProductDto.CproductSkuKv::getCode))), ArrayList::new)
                    ));
                    result.setSkuKvTwo(skuKvTwo.stream().collect(
                            Collectors.collectingAndThen(
                                    Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(CrawlerProductDto.CproductSkuKv::getCode))), ArrayList::new)
                    ));
                    result.setSkuPrices(skuPrices);
                }else {

                    throw new IOException();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

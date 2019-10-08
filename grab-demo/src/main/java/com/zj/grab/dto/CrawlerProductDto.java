package com.zj.grab.dto;

import com.alibaba.fastjson.JSONObject;


import java.io.Serializable;
import java.util.List;

/**
 * 爬取商品dto
 */
public class CrawlerProductDto implements Serializable {

    private static final long serialVersionUID = -7927343307307759292L;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 原商品ID
     */
    private Long productId;
    /**
     * 商品详情
     */
    private String goodsDesc;
    /**
     * 商品主图图片集合
     */
    private List<String> imageUrls;
    /**
     * 商品价格
     */
    private String price;
    /**
     * 商品原价
     */
    private String originalPrice;
    /**
     * sku价格组合
     */
    private List<CproductSkuMap> skuPrices;

    private JSONObject priceJsonObj;
    /**
     * 规格1
     */
    private List<CproductSkuKv> skuKvOne;


    private List<String> skuOneImg;

    private List<String> skuTwoImg;
    /**
     * 规格1 分类名称
     */
    private String skuOneName;
    /**
     * 规格2 分类名称
     */
    private String skuTwoName;
    /**
     * 规格2
     */
    private List<CproductSkuKv> skuKvTwo;
    /**
     * 详情文本
     */
    private String content;
    /**
     * 详情图片
     */
    private List<String> productDetailUrls;
    /**
     * 店铺Id
     */
    private String mallId;

    private JSONObject skuPriceObj;


    /**
     *  商品规格数量
     */
    private Integer skuSize = 0;


    private String skuImgList;


    public List<String> getSkuOneImg() {
        return skuOneImg;
    }

    public void setSkuOneImg(List<String> skuOneImg) {
        this.skuOneImg = skuOneImg;
    }

    public List<String> getSkuTwoImg() {
        return skuTwoImg;
    }

    public void setSkuTwoImg(List<String> skuTwoImg) {
        this.skuTwoImg = skuTwoImg;
    }

    public String getSkuImgList() {
        return skuImgList;
    }

    public void setSkuImgList(String skuImgList) {
        this.skuImgList = skuImgList;
    }

    public Integer getSkuSize() {
        return skuSize;
    }

    public void setSkuSize(Integer skuSize) {
        this.skuSize = skuSize;
    }



    public JSONObject getSkuPriceObj() {
        return skuPriceObj;
    }

    public void setSkuPriceObj(JSONObject skuPriceObj) {
        this.skuPriceObj = skuPriceObj;
    }

    public String getMallId() {
        return mallId;
    }

    public void setMallId(String mallId) {
        this.mallId = mallId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public String getSkuOneName() {
        return skuOneName;
    }

    public void setSkuOneName(String skuOneName) {
        this.skuOneName = skuOneName;
    }

    public String getSkuTwoName() {
        return skuTwoName;
    }

    public void setSkuTwoName(String skuTwoName) {
        this.skuTwoName = skuTwoName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public List<CproductSkuMap> getSkuPrices() {
        return skuPrices;
    }

    public void setSkuPrices(List<CproductSkuMap> skuPrices) {
        this.skuPrices = skuPrices;
    }

    public List<CproductSkuKv> getSkuKvOne() {
        return skuKvOne;
    }

    public void setSkuKvOne(List<CproductSkuKv> skuKvOne) {
        this.skuKvOne = skuKvOne;
    }

    public List<CproductSkuKv> getSkuKvTwo() {
        return skuKvTwo;
    }

    public void setSkuKvTwo(List<CproductSkuKv> skuKvTwo) {
        this.skuKvTwo = skuKvTwo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getProductDetailUrls() {
        return productDetailUrls;
    }

    public void setProductDetailUrls(List<String> productDetailUrls) {
        this.productDetailUrls = productDetailUrls;
    }

    public static class CproductSkuMap implements Serializable{

        private static final long serialVersionUID = -2043451082445698216L;
        private String code;
        /**售价*/
        private String price;
        /**原价*/
        private String originalPrice;
        private Integer quantity;


        public CproductSkuMap(String code, String price) {
            this.code = code;
            this.price = price;
        }

        public CproductSkuMap(String code, String price, String originalPrice, Integer quantity) {
            this.code = code;
            this.price = price;
            this.originalPrice = originalPrice;
            this.quantity = quantity;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public CproductSkuMap() {
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getOriginalPrice() {
            return originalPrice;
        }

        public void setOriginalPrice(String originalPrice) {
            this.originalPrice = originalPrice;
        }
    }
    public static class CproductSkuKv implements Serializable{

        private static final long serialVersionUID = -5046250717881057973L;
        private String code;
        private String name;
        public CproductSkuKv() {
        }
        public CproductSkuKv(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

//    @Override
//    public String toString() {
//        return "CrawlerProductDto{" +
//                "name='" + name + '\'' +
//                ", goodsDesc='" + goodsDesc + '\'' +
//                ", imageUrls=" + imageUrls +
//                ", price='" + price + '\'' +
//                ", originalPrice='" + originalPrice + '\'' +
//                ", skuPrices=" + JSON.toJSONString(skuPrices) +
//                ", skuKvOne=" + JSON.toJSONString(skuKvOne) +
//                ", skuKvTwo=" + JSON.toJSONString(skuKvTwo) +
//                ", content='" + content + '\'' +
//                ", productDetailUrls=" + productDetailUrls +
//                '}';
//    }
}

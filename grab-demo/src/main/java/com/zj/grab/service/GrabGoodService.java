package com.zj.grab.service;

import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import com.zj.grab.dto.CrawlerProductDto;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhoujian
 * @Date: 2019/10/8 10:11
 * @Company: youanmi.
 * @Desc:
 */
public abstract class GrabGoodService {
    /**
     * 爬取商品信息
     * @param code
     */
    protected abstract CrawlerProductDto grab( String code ) throws IOException;

    /**
     * 解析淘宝/天猫口令
     * @param code
     * @return
     */
    public abstract String parseGetGoodId(String code );


    /**
     * 爬取商品信息，由于jd pdd 获取失败几率比较高 所以加上重试
     * @param code
     * @return
     */
   public CrawlerProductDto grabGoods( String code ){
       Retryer<CrawlerProductDto> retryer = RetryerBuilder.<CrawlerProductDto>newBuilder().retryIfException()
               .withWaitStrategy(WaitStrategies.fixedWait(0, TimeUnit.MILLISECONDS))
               .withStopStrategy(StopStrategies.stopAfterAttempt(5))
               .build();
       try {
           retryer.call(() -> this.grab(code));
       } catch (Exception e) {
           e.printStackTrace();
       }
       return null;
   }




}

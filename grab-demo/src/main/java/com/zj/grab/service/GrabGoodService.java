package com.zj.grab.service;

import com.github.rholder.retry.*;
import com.zj.grab.dto.CrawlerProductDto;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
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
       } catch (ExecutionException e) {
           e.printStackTrace();
       } catch (RetryException e) {
           e.printStackTrace();
       }
       return null;
   }




}

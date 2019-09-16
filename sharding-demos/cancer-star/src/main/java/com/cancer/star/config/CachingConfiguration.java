package com.cancer.star.config;


import com.cancer.star.constants.EhCacheConstants;
import com.cancer.star.constants.StarConstants;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;

import net.sf.ehcache.config.CacheConfiguration;

/**
 /**
 * @Auther: zhoujian
 * @Date: 2018/7/18 16:53
 * @Company: youanmi.
 * @Desc:ehCache配置
 */
@Configuration
@EnableCaching
public class CachingConfiguration implements CachingConfigurer {

    @Bean(destroyMethod="shutdown")
    public net.sf.ehcache.CacheManager ehCacheManager() {
        CacheConfiguration userCacheConfig = new CacheConfiguration();
        userCacheConfig.setName(EhCacheConstants.EhcacheKeys.USERS);
        userCacheConfig.setMemoryStoreEvictionPolicy("LRU");
        userCacheConfig.setMaxEntriesLocalHeap(1000);
        userCacheConfig.setDiskExpiryThreadIntervalSeconds(2);


        net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
        config.addCache(userCacheConfig);
        return net.sf.ehcache.CacheManager.newInstance(config);
    }

    @Bean
    @Override
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(ehCacheManager());
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }

    @Override
    public CacheResolver cacheResolver() {
        return null;
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return null;
    }

}

package com.cancer.sphere.config;

import io.shardingsphere.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.util.Collection;

/**
 * @author: zhoujian
 * @Date: 2018/10/11 15:58
 * @Company: youanmi.
 * @Desc:分库
 */
public final class DatabaseShardingAlgorithm implements PreciseShardingAlgorithm<Integer> {

    @Override
    public String doSharding(final Collection<String> availableTargetNames, final PreciseShardingValue<Integer> shardingValue) {
        int size = availableTargetNames.size();
        for (String each : availableTargetNames) {
            if (each.endsWith(shardingValue.getValue() % size + "")) {
                return each;
            }
        }
        throw new UnsupportedOperationException();
    }
}

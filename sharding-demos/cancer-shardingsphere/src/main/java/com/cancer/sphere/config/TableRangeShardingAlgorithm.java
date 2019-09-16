package com.cancer.sphere.config;

import com.google.common.collect.Range;
import io.shardingsphere.core.api.algorithm.sharding.RangeShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.standard.RangeShardingAlgorithm;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author: zhoujian
 * @Date: 2018/10/11 16:06
 * @Company: youanmi.
 * @Desc:
 */
public final class TableRangeShardingAlgorithm implements RangeShardingAlgorithm<Long> {

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Long> rangeShardingValue) {
        int size = collection.size();
        Collection<String> collect = new ArrayList<>();
        Range<Long> valueRange = rangeShardingValue.getValueRange();
        for (Long i = valueRange.lowerEndpoint(); i <= valueRange.upperEndpoint(); i++) {
            for (String each : collection) {
                if (each.endsWith(i % size + "")) {
                    collect.add(each);
                }
            }
        }
        return collect;
    }
}

package com.cancer.share.config;

/**
 * Created by zhoujian on 2018/7/4
 *
 * @Desc 类描述.
 */
import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.google.common.collect.Range;

import java.util.Collection;
import java.util.LinkedHashSet;


/**
 * Created by zhoujian on 2018/4/9.
 * 分库
 */
public class ModuloDatabaseShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm<Long> {

    @Override
    public String doEqualSharding(Collection<String> availableTargetNames, ShardingValue<Long> shardingValue) {
        for (String each : availableTargetNames) {
            if (each.endsWith(shardingValue.getValue() % 2 + "")) {
                return each;
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Collection<String> doInSharding(Collection<String> availableTargetNames, ShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        for (Long value : shardingValue.getValues()) {
            for (String tableName : availableTargetNames) {
                if (tableName.endsWith(value % 2 + "")) {
                    result.add(tableName);
                }
            }
        }
        return result;
    }

    @Override
    public Collection<String> doBetweenSharding(Collection<String> availableTargetNames,
                                                ShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        Range<Long> range = shardingValue.getValueRange();
        for (Long i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
            for (String each : availableTargetNames) {
                if (each.endsWith(i % 2 + "")) {
                    result.add(each);
                }
            }
        }
        return result;
    }

}

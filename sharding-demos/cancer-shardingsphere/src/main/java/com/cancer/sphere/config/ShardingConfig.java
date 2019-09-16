package com.cancer.sphere.config;

/**
 * @author: zhoujian
 * @Date: 2018/10/11 16:07
 * @Company: youanmi.
 * @Desc:
 */

import com.alibaba.druid.pool.DruidDataSource;
import io.shardingsphere.core.api.ShardingDataSourceFactory;
import io.shardingsphere.core.api.config.ShardingRuleConfiguration;
import io.shardingsphere.core.api.config.TableRuleConfiguration;
import io.shardingsphere.core.api.config.strategy.StandardShardingStrategyConfiguration;
import io.shardingsphere.core.keygen.DefaultKeyGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
@Configuration
@MapperScan(basePackages = {"com.cancer.sphere.db.mapper"})
public class ShardingConfig {

    @ConfigurationProperties(prefix = "spring0.datasource")
    @Bean(name = "ds_0")
    public DataSource dataSource0() {
        return new DruidDataSource();
    }

    @ConfigurationProperties(prefix = "spring1.datasource")
    @Bean(name = "ds_1")
    public DataSource dataSource1() {
        return new DruidDataSource();
    }

    @Primary
    @Bean(name = "shardingDataSource")
    public DataSource getDataSource(@Qualifier("ds_0") DataSource ds_0,
                                    @Qualifier("ds_1") DataSource ds_1) throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(getOrderTableRuleConfiguration());
        shardingRuleConfig.getTableRuleConfigs().add(getOrderItemTableRuleConfiguration());
        shardingRuleConfig.getTableRuleConfigs().add(getUserTableRuleConfiguration());
        shardingRuleConfig.getBindingTableGroups().add("t_order, t_order_item ,user");
       // shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_id", new DatabaseShardingAlgorithm()));
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("order_id", new TablePreciseShardingAlgorithm(), new TableRangeShardingAlgorithm()));
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("id", new TablePreciseShardingAlgorithm(), new TableRangeShardingAlgorithm()));
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("ds_0", ds_0);
//        dataSourceMap.put("ds_1", ds_1);
        Properties properties = new Properties();
       // properties.setProperty("sql.show", Boolean.TRUE.toString());
        return ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new HashMap<String, Object>(), properties);
    }

    private static TableRuleConfiguration getOrderTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration();
        result.setLogicTable("t_order");
      //  result.setActualDataNodes("ds_${0..1}.t_order_${[0, 1]}");
        result.setActualDataNodes("ds_0.t_order_${[0, 1]}");
        result.setKeyGeneratorColumnName("order_id");
        return result;
    }

    private static TableRuleConfiguration getOrderItemTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration();
        result.setLogicTable("t_order_item");
        //result.setActualDataNodes("ds_${0..1}.t_order_item_${[0, 1]}");
        result.setActualDataNodes("ds_0.t_order_item_${[0, 1]}");
        return result;
    }

    private static TableRuleConfiguration getUserTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration();
        result.setLogicTable("user");
        //result.setActualDataNodes("ds_${0..1}.t_order_item_${[0, 1]}");
        result.setActualDataNodes("ds_0.user_${[0, 1]}");
        result.setKeyGeneratorColumnName("id");
        return result;
    }


}

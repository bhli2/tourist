package com.qbk.source.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.qbk.source.config.datasource.DynamicDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    /**
     * 读取数据源配置
     */
    @Bean()
    @ConfigurationProperties(prefix = "spring.datasource.druid.first")
    public DataSource dataSourceFirst(){
        //druid 数据源
        return DruidDataSourceBuilder.create().build();
    }

    @Bean()
    @ConfigurationProperties(prefix = "spring.datasource.druid.second")
    public DataSource dataSourceSecond(){
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        dataSource.setName("second");
        return dataSource;
    }

    /**
     * 自定义数数据路由（也是数据源）
     */
    @Primary
    @Bean("dynamicDataSource")
    public DynamicDataSource dynamicDataSource(){
        //创建数据源集合
        Map<Object,Object> targetDataSource = new HashMap<>();
        targetDataSource.put("first",dataSourceFirst());
        targetDataSource.put("second",dataSourceSecond());
        //创建数据路由对象
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        //设置数据源集合
        dynamicDataSource.setTargetDataSources(targetDataSource);
        //设置默认数据源
        dynamicDataSource.setDefaultTargetDataSource(dataSourceFirst());
        return dynamicDataSource ;
    }

}

package com.qbk.nosql.elasticsearchdemo.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 集成 restHighLevelClient 客户端
 *  restHighLevelClient 采用rest方式
 *
 * 相较于TransportClient，RestHighLevelClient最优之处在于可以兼容当前版本及以后版本的ES Cluster，
 *  而TransportClient却必须和ES Cluster版本一致否则报错。
 *  而且RestHighLevelClient和TransportClient API很类似，包括参数和返回数据，比较容易迁移。
 *  TransportClient将在7中deprecated，8中删除。
 *
 *  目前spring-data-elasticsearch底层采用es官方TransportClient，
 *  而es官方计划放弃TransportClient，工具以es官方推荐的RestHighLevelClient进行封装
 */
@Configuration
public class ElasticSearchConfig {

    /**
     * 使用 restHighLevelClient 客户端
     * 连接的端口是 9200
     */
    @Bean(name = "restHighLevelClient")
    public RestHighLevelClient client() {
        HttpHost httpHost= new HttpHost("172.31.54.234",9200);
        RestClientBuilder builder = RestClient.builder(httpHost);
        return new RestHighLevelClient(builder);
    }
}

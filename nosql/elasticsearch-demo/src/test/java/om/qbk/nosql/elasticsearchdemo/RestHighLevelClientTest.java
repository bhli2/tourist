package om.qbk.nosql.elasticsearchdemo;

import com.qbk.nosql.elasticsearchdemo.ElasticSearchDemoApplication;
import com.qbk.nosql.elasticsearchdemo.entity.Item;
import org.apache.commons.beanutils.BeanUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * restHighLevelClient 客户端测试
 */
@SpringBootTest(classes = ElasticSearchDemoApplication.class)
public class RestHighLevelClientTest {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     *  查询
     */
    @Test
    public void createIndex() throws IOException, InvocationTargetException, IllegalAccessException {
        //"qbk "是es中的索引
        //SearchRequest是包装查询请求
        SearchRequest searchRequest = new SearchRequest("qbk");
        //sourceBuilder 是用来构建查询条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //添加查询条件就和transportClient中的一样了
        sourceBuilder.query(
                QueryBuilders.boolQuery()
                        .must(
                                QueryBuilders.matchQuery("title", "人民5")
                        )
        );
        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest);

        SearchHit[] searchHits = searchResponse.getHits().getHits();
        //Item 是我定义的实体类
        ArrayList<Item> list = new ArrayList<>();
        //用来转换存储查询到的
        for (SearchHit hit : searchHits) {
            float score = hit.getScore();
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            if(score >= 1.0 ){
                System.out.println("符合分数的是:" + sourceAsMap + "得分:" + score );
            }else {
                System.out.println("不符合分数的是:" + sourceAsMap + "得分:" + score );
            }
            Item item = new Item();
            //map赋值给实体类对象（key和对象属性务必一致）
            BeanUtils.populate(item,sourceAsMap);
            list.add(item);
        }
        list.forEach(System.out::println);
    }

    /**
     * 打印结果集
     * @param searchHits SearchHit[]
     */
    private void printResult(SearchHit[] searchHits) {
        int num = 0;
        for (SearchHit searchHit : searchHits) {
            num++;
            // String index = searchHit.getIndex();
            String id = searchHit.getId();
            float score = searchHit.getScore();

            System.out.println("第" + num + "条数据:********" + "id:"+id + "，分数："+score);

            String sourceAsString = searchHit.getSourceAsString();
            // search结果字符串
            System.out.println("result String:" + sourceAsString);
            // search 结果map对象
            Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
            System.out.println(sourceAsMap);
        }
    }

    /**
     * 搜索 索引中所有的doc
     */
    @Test
    public void testSearchAll() throws IOException {
        // 基础设置
        SearchRequest searchRequest = new SearchRequest("qbk");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 搜索方式
        searchSourceBuilder.query(
                QueryBuilders.matchAllQuery()
        );
        searchRequest.source(searchSourceBuilder);

        // 返回字段过滤（可选）
        // 第一个参数为结果集包含哪些字段，第二个参数为结果集不包含哪些字段
        searchSourceBuilder.fetchSource(new String[]{"title","category"}, new String[]{});

        // 发起请求，获取结果
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
        SearchHits hits = searchResponse.getHits();
        // 得到匹配度高的文档
        SearchHit[] searchHits = hits.getHits();
        // 打印结果集
        printResult(searchHits);
    }

    /**
     * 以分页查询的请求方式得到结果
     */
    @Test
    public void testPageSearch() throws IOException {
        // 基础设置
        SearchRequest searchRequest = new SearchRequest("qbk");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 设置分页参数
        searchSourceBuilder.from(2).size(3);
        // 搜索方式
        searchSourceBuilder.query(
                QueryBuilders.matchAllQuery()
        );
        searchRequest.source(searchSourceBuilder);
        // 发起请求，获取结果
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest );
        SearchHits hits = searchResponse.getHits();
        // 得到匹配度高的文档
        SearchHit[] searchHits = hits.getHits();
        // 打印结果集
        printResult(searchHits);
    }

    /**
     * term query查询 该查询为精确查询（不会对查询条件进行分词），在查询时会以查询条件整体去匹配词库中的词（分词后的单个词）
     */
    @Test
    public void testTermSearch() throws IOException {
        // 基础设置
        SearchRequest searchRequest = new SearchRequest("qbk");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 搜索方式
        searchSourceBuilder.query(
                QueryBuilders.termQuery("title", "中华人民")
        );
        searchRequest.source(searchSourceBuilder);
        // 发起请求，获取结果
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest );
        SearchHits hits = searchResponse.getHits();
        // 得到匹配度高的文档
        SearchHit[] searchHits = hits.getHits();
        // 打印结果集
        printResult(searchHits);
    }

    /**
     * 根据id进行精确查询
     */
    @Test
    public void testSearchById() throws IOException {
        // 基础设置
        SearchRequest searchRequest = new SearchRequest("qbk");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        List<String> idList = new ArrayList<>();
        idList.add("200");
        idList.add("202");
        // 搜索方式
        searchSourceBuilder.query(
                QueryBuilders.termsQuery("id", idList)
        );
        searchRequest.source(searchSourceBuilder);
        // 发起请求，获取结果
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest );
        SearchHits hits = searchResponse.getHits();
        // 得到匹配度高的文档
        SearchHit[] searchHits = hits.getHits();
        // 打印结果集
        printResult(searchHits);
    }

    /**
     * 根据关键字搜索
     */
    @Test
    public void testMatchSearch() throws IOException {
        // 基础设置
        SearchRequest searchRequest = new SearchRequest("qbk");
        // 搜索源构建对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 匹配关键字

        // 写法一：会将"spring实战"分成两个词，只有要有一个匹配成功，则返回该文档(Operator.OR)
        searchSourceBuilder.query(
                QueryBuilders.matchQuery("title", "人民5")
                        .operator(Operator.OR)
        );

        // 写法二:只要有两个词匹配成功，则返回文档（如果是3个词，则是0.7*3，向下取整得到2，匹配到两个词则返回文档）
//        searchSourceBuilder.query(
//                QueryBuilders.matchQuery("title", "人民5")
//                .minimumShouldMatch("70%")
//        );

        searchRequest.source(searchSourceBuilder);
        // 发起请求，获取结果
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest );
        SearchHits hits = searchResponse.getHits();
        // 得到匹配度高的文档
        SearchHit[] searchHits = hits.getHits();
        // 打印结果集
        printResult(searchHits);
    }

    /**
     * 根据关键字搜索（多个域）
     */
    @Test
    public void testMultiMatchSearch() throws IOException {
        // 基础设置
        SearchRequest searchRequest = new SearchRequest("qbk");
        // 搜索源构建对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // matchQuery（title 、category 两个字段为搜索域，并且至少匹配到70%的词）
        MultiMatchQueryBuilder multiMatchQueryBuilder =
                QueryBuilders.multiMatchQuery("测试3", "title", "category")
                        .minimumShouldMatch("70%")
                        .field("title", 10) ;
        searchSourceBuilder.query(multiMatchQueryBuilder);
        // 给搜索请求对象设置搜索源
        searchRequest.source(searchSourceBuilder);
        // 发起请求，获取结果
        SearchResponse searchResponse =  restHighLevelClient.search(searchRequest );
        SearchHits hits = searchResponse.getHits();
        // 得到匹配度高的文档
        SearchHit[] searchHits = hits.getHits();
        // 打印结果集
        printResult(searchHits);
    }

    /**
     * 布尔查询
     */
    @Test
    public void testBoolSearch() throws IOException {
        // 基础设置
        SearchRequest searchRequest = new SearchRequest("qbk");
        // 搜索源构建对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // bool搜索
        // 第一个query
        MultiMatchQueryBuilder multiMatchQueryBuilder =
                QueryBuilders.multiMatchQuery("测试3", "title", "category")
                        .minimumShouldMatch("70%")
                        .field("title", 10);
        // 第二个query
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("price", 2.2);

        // 定义一个布尔query，将上面两个条件组合在一起
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(multiMatchQueryBuilder);
        boolQueryBuilder.must(termQueryBuilder);

        searchSourceBuilder.query(boolQueryBuilder);

        // 给搜索请求对象设置搜索源
        searchRequest.source(searchSourceBuilder);
        // 发起请求，获取结果
        SearchResponse searchResponse =  restHighLevelClient.search(searchRequest );
        SearchHits hits = searchResponse.getHits();
        // 得到匹配度高的文档
        SearchHit[] searchHits = hits.getHits();
        // 打印结果集
        printResult(searchHits);
    }

    /**
     * 过滤器（对于搜索结果的过滤，效率高，推荐) + 排序
     */
    @Test
    public void testFilter() throws IOException {
        // 基础设置
        SearchRequest searchRequest = new SearchRequest("qbk");
        // 搜索源构建对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // bool搜索
        // 第一个query
        MultiMatchQueryBuilder multiMatchQueryBuilder =
                QueryBuilders.multiMatchQuery("测试3", "title", "category")
                        .minimumShouldMatch("70%")
                        .field("title", 10);
        // 第二个query
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("price", 2.2);
        // 定义一个布尔query，将上面两个条件组合在一起
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(multiMatchQueryBuilder);
        boolQueryBuilder.must(termQueryBuilder);
        // 过滤器
        boolQueryBuilder.filter(
                QueryBuilders.termQuery("price", 2.2)
        );
        // 设置
        searchSourceBuilder.query(boolQueryBuilder);
        // 定义排序
        searchSourceBuilder.sort(new FieldSortBuilder("price").order(SortOrder.ASC));

        // 给搜索请求对象设置搜索源
        searchRequest.source(searchSourceBuilder);
        // 发起请求，获取结果
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest );
        SearchHits hits = searchResponse.getHits();
        // 得到匹配度高的文档
        SearchHit[] searchHits = hits.getHits();
        // 打印结果集
        printResult(searchHits);
    }
}

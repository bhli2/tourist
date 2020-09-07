package om.qbk.nosql.elasticsearchdemo;

import com.qbk.nosql.elasticsearchdemo.ElasticSearchDemoApplication;
import com.qbk.nosql.elasticsearchdemo.entity.Item;
import org.apache.commons.beanutils.BeanUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Map;

/**
 * restHighLevelClient 客户端测试
 */
@SpringBootTest(classes = ElasticSearchDemoApplication.class)
public class RestHighLevelClientTest {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 查询
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
                                QueryBuilders.termsQuery("title", "人民")
                        )
        );
        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest);

        SearchHit[] searchHits = searchResponse.getHits().getHits();
        //Item 是我定义的实体类
        ArrayList<Item> list = new ArrayList<>();
        //用来转换存储查询到的
        for (SearchHit hit : searchHits) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            Item item = new Item();
            //map赋值给实体类对象（key和对象属性务必一致）
            BeanUtils.populate(item,sourceAsMap);
            list.add(item);
        }
        list.forEach(System.out::println);
    }
}

package org.vidi.es.example.high;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.vidi.es.example.high.util.IndexActionListener;

import java.io.IOException;

/**
 * @author vidi
 * @date 2019-02-01
 */
public class IndexSample {
    public static void main(String[] args) throws IOException, InterruptedException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("127.0.0.1", 9200, "http"))
        );

        IndexRequest request = new IndexRequest("post", "ss", "1");
        String jsonString = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        request.source(jsonString, XContentType.JSON);

        client.indexAsync(request, RequestOptions.DEFAULT, new IndexActionListener());
        Thread.sleep(10000);
        client.close();
    }
}

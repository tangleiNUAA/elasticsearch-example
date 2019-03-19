package org.vidi.es.example.high;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.vidi.es.example.high.util.SearchActionListener;

import java.io.IOException;

/**
 * @author vidi
 * @date 2019-02-02
 */
public class QuerySample {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("127.0.0.1", 9200, "http"))
        );

        SearchRequest request = new SearchRequest();
        client.searchAsync(request.indices("post"), RequestOptions.DEFAULT, new SearchActionListener());
        client.close();
    }
}

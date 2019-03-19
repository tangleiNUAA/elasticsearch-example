package org.vidi.es.example.high;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.vidi.es.example.high.util.SearchActionListener;

/**
 * @author vidi
 * @date 2019-02-02
 */
public class AggregationSample {

    public static void main(String[] args) {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("127.0.0.1", 9200, "http")));
        // kibana_sample_data_flights
        // kibana_sample_data_ecommerce
        // kibana_sample_data_logs
        SearchRequest request = new SearchRequest("kibana_sample_data_flights");
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("kibana_sample_data_flights", "");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(matchQueryBuilder);
        request.source(searchSourceBuilder);
        client.searchAsync(request, RequestOptions.DEFAULT, new SearchActionListener());
    }
}

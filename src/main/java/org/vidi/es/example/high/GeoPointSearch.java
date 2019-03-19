package org.vidi.es.example.high;

        import org.apache.http.HttpHost;
        import org.elasticsearch.action.search.SearchRequest;
        import org.elasticsearch.client.RequestOptions;
        import org.elasticsearch.client.RestClient;
        import org.elasticsearch.client.RestHighLevelClient;
        import org.elasticsearch.common.unit.DistanceUnit;
        import org.elasticsearch.index.query.QueryBuilders;
        import org.elasticsearch.search.builder.SearchSourceBuilder;
        import org.vidi.es.example.high.util.SearchActionListener;

/**
 * @author vidi
 * @date 2019-02-09
 */
public class GeoPointSearch {
    public static void main(String[] args) {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("127.0.0.1", 9200, "http"))
        );

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder
                .query(QueryBuilders.geoDistanceQuery("kibana_sample_data_flights geo search")
                        .point(34, 108)
                        .distance(1000, DistanceUnit.KILOMETERS));

        SearchRequest request = new SearchRequest("kibana_sample_data_flights");
        request.source(searchSourceBuilder);
        client.searchAsync(request, RequestOptions.DEFAULT, new SearchActionListener());
    }
}

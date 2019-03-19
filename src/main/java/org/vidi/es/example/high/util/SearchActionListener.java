package org.vidi.es.example.high.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.search.SearchResponse;

import java.util.stream.Stream;

/**
 * @author vidi
 * @date 2019-02-02
 */
public class SearchActionListener implements ActionListener<SearchResponse> {
    private static final Log LOG = LogFactory.getLog(SearchActionListener.class);

    @Override
    public void onResponse(SearchResponse searchResponse) {
        // Executions after response.
        System.out.println("Start search response.");
        System.out.println(searchResponse.getHits().totalHits);
        Stream.of(searchResponse.getHits().getHits()).forEach(hit -> {
            System.out.println(hit.getIndex());
            System.out.println(hit.getSourceAsMap());
        });
    }

    @Override
    public void onFailure(Exception e) {
        // Executions while failed to get response.
        LOG.error("failed search", e);
        LOG.error(e.getCause());
    }
}

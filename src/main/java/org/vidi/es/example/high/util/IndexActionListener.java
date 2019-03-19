package org.vidi.es.example.high.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexResponse;

/**
 * @author vidi
 * @date 2019-02-01
 */
public class IndexActionListener implements ActionListener<IndexResponse> {
    private static final Log LOG = LogFactory.getLog(IndexActionListener.class);

    @Override
    public void onResponse(IndexResponse indexResponse) {
        // Executions after response.
        System.out.println(indexResponse.status());
        System.out.println(indexResponse.getIndex());
    }

    @Override
    public void onFailure(Exception e) {
        // Executions while failed to get response.
        LOG.info("Failed get", e);
    }

}

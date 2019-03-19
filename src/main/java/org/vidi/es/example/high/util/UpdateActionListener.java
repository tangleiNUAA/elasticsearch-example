package org.vidi.es.example.high.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.update.UpdateResponse;

/**
 * @author vidi
 * @date 2019-02-01
 */
public class UpdateActionListener implements ActionListener<UpdateResponse> {
    private static final Log LOG= LogFactory.getLog(UpdateActionListener.class);

    @Override
    public void onResponse(UpdateResponse updateResponse) {
        // Executions after response.
    }

    @Override
    public void onFailure(Exception e) {
        // Executions while failed to get response.
    }
}

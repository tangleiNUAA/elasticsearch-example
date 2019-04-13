package org.vidi.es.example.admin;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.vidi.es.example.util.ClientUtil;
import org.vidi.es.example.util.HelperContents;

import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

/**
 * @author vidi
 * @date 2018-12-19
 */
public class IndicesOperations {

    public static void createIndex() throws UnknownHostException, ExecutionException, InterruptedException {
        TransportClient client = ClientUtil.getClient();
        CreateIndexRequest request = new CreateIndexRequest();
        request.index(HelperContents.CREATE_INDEX_NAME);
        // Define mapping for this index.
        // request.mapping("", other params)

        // Define settings for this index.
        //request.settings(source)
        client.admin().indices().create(request).get();
        client.close();
    }

    public static void main(String[] args) throws UnknownHostException, ExecutionException, InterruptedException {
        createIndex();
    }

}

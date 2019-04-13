package org.vidi.es.example.document;

import com.google.common.base.Charsets;
import org.apache.commons.io.FileUtils;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.vidi.es.example.util.ClientUtil;
import org.vidi.es.example.util.HelperContents;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author vidi
 * @date 2018-12-19
 */
public class PutOperation {
    public void putData() throws UnknownHostException {
        TransportClient client = ClientUtil.getClient();
        File file = new File("data");
        File[] files = file.listFiles();
        Stream.of(Objects.requireNonNull(files)).forEach(aFile -> {
            try {
                String json = FileUtils.readFileToString(aFile, Charsets.UTF_8);
                String index = HelperContents.CREATE_INDEX_NAME;

                client.prepareIndex(index, "_doc")
                        .setSource(json, XContentType.JSON)
                        .get();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        client.close();
    }

    public static void main(String[] args) throws UnknownHostException {
        PutOperation operations = new PutOperation();
        operations.putData();
    }
}

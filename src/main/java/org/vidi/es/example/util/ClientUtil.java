package org.vidi.es.example.util;

import com.google.common.io.Resources;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.ini4j.Config;
import org.ini4j.Ini;
import org.ini4j.Profile;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取 TransportClient
 *
 * @author vidi
 * @date 2018-12-18
 */
public class ClientUtil {

    private static Integer port;
    private static String host;
    private static Map<String, String> conf = new HashMap<>();

    private static Settings settings() {
        loadConfig();
        return Settings.builder()
                .putProperties(conf, t -> t)
                .build();
    }

    private static void loadConfig() {
        Config config = new Config();
        config.setMultiSection(true);
        URL url = Resources.getResource("example.ini");
        Ini ini = new Ini();
        ini.setConfig(config);
        try {
            ini.load(url);
            Profile.Section baseSection = ini.get("base");
            host = baseSection.get("host");
            port = Integer.parseInt(baseSection.get("port"));
            System.out.println(host + ":" + port);
            Profile.Section clusterSection = ini.get("cluster");
            clusterSection.forEach(conf::put);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TransportClient getClient() throws UnknownHostException {
        return new PreBuiltTransportClient(settings())
                .addTransportAddress(new TransportAddress(InetAddress.getByName(host), port));
    }
}

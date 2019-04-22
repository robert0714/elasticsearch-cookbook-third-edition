package com.packtpub;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.SecureString;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.node.InternalSettingsPreparer;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeValidationException; 
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.transport.Netty4Plugin;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import io.netty.handler.codec.base64.Base64Encoder;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

public class NativeClient {
    public static Client createTransportClient() throws UnknownHostException {
        final Settings settings = Settings.builder()
                .put("client.transport.sniff", true)
                .put("cluster.name", "elasticsearch").build();
        //https://www.elastic.co/guide/en/cloud-enterprise/current/security-transport.html
        TransportClient client = new PreBuiltTransportClient(settings);

        return client
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
    }

    private final Node node;
    private final Client client;

    private static class PluginNode extends Node {
        public PluginNode(Settings preparedSettings, List<Class<? extends Plugin>> plugins) {
            super(InternalSettingsPreparer.prepareEnvironment(preparedSettings, null), plugins);
        }
    }


    public NativeClient() throws NodeValidationException {
        final Settings settings = Settings.builder()
                .put("path.home", "/tmp")
                .put("client.transport.sniff", true)
                .put("cluster.name", "elasticsearch") 
                .put("node.data", false)
                .put("node.master", false)
                .put("node.ingest", false).build();
        
       
        
        
        node = new PluginNode(settings, Collections.<Class<? extends Plugin>>singletonList(Netty4Plugin.class));
        node.start();
        client = node.client();
        
        
        String token = basicAuthHeaderValue("elastic", new SecureString("changeme".toCharArray()));
        
        client.filterWithHeader(Collections.singletonMap("Authorization", token))
        .prepareSearch().get();
    }

    private String basicAuthHeaderValue(String user, SecureString pwd) {
    	return Base64.getEncoder().encodeToString((user + ":" + pwd).getBytes()); 
	}

	public Client getClient() {
        return client;
    }

    public void close() throws IOException {
        client.close();
        node.close();
    }
}

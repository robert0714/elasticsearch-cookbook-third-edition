package com.packtpub;

import org.apache.commons.io.IOUtils;
import org.apache.http.*;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients; 
import org.apache.http.util.EntityUtils;

import java.io.*; 

public class App {

	private static String wsUrl = "http://127.0.0.1:9200"; 

	public static void main(String[] args) {
		method1();
	}

	public static void method1() {
		final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("elastic", "changeme"));

		CloseableHttpClient client = HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider).
				setRetryHandler(new MyRequestRetryHandler()).build();
		
		HttpGet method = new HttpGet(wsUrl + "/test-index/test-type/1");
		// Execute the method.

		try {
			CloseableHttpResponse response = client.execute(method);

			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + response.getStatusLine());
				HttpEntity entity = response.getEntity();
				String responseBody = EntityUtils.toString(entity);
				System.err.println(responseBody);
			} else {
				HttpEntity entity = response.getEntity();
				String responseBody = EntityUtils.toString(entity);
				System.out.println(responseBody);
			}

		} catch (IOException e) {
			System.err.println("Fatal transport error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
	}

	 
}

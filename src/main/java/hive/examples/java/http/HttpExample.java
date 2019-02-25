package hive.examples.java.http;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpExample {

    /**
     * The URL that is going to be used for the example HTTP call.
     */
    private static String EXAMPLE_HTTP_URL = "https://libs.stage.hive.pt/";

    public static void run() throws IOException {
        exampleHttp();
    }

    /**
     * Runs the typical HTTP request to be expected URL.
     *
     * @throws IOException Raises when an error occurs with the request.
     */
    private static void exampleHttp() throws IOException {
        exampleHttp(EXAMPLE_HTTP_URL);
    }

    /**
     * Runs a simple HTTP request according to the provided URL string.
     * The resulting value is going to be printed to the stdout.
     *
     * @param url The URL to retrieve the HTTP request.
     * @throws IOException Raises when an error occurs with the request.
     */
    private static void exampleHttp(String url) throws IOException {
        CloseableHttpResponse response;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpget = new HttpGet(url);
            response = httpClient.execute(httpget);
            try {
                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    System.out.println(line);
                }
            } finally {
                response.close();
            }
        }
    }
}

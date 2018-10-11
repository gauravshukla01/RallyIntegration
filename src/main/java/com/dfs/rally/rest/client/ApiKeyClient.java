package com.dfs.rally.rest.client;

import org.apache.http.client.methods.HttpRequestBase;

import java.io.IOException;
import java.net.URI;

/**
 * <b>Description:</b> A HttpClient which authenticates using an API Key.

 * 
 *
 * 
 */
public class ApiKeyClient extends HttpClient {

    protected String apiKey;
    protected static final String API_KEY_HEADER = "zsessionid";

    /**
     * <b>Description:</b> Construct a new client.

     */
    public ApiKeyClient(URI server, String apiKey) {
        super(server);
        this.apiKey = apiKey;
    }

    /**
     * <b>Description:</b> Execute a request against the WSAPI
     *
     *
     */
    @Override
    protected String doRequest(HttpRequestBase request) throws IOException {
        request.setHeader(API_KEY_HEADER, this.apiKey);
        return super.doRequest(request);
    }
}
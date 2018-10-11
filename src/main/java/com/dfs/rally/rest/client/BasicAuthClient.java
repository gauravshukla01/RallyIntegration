package com.dfs.rally.rest.client;

import com.dfs.rally.rest.response.GetResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import jline.internal.Log;

import org.apache.http.auth.Credentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.auth.BasicScheme;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * <b>Description:</b> A HttpClient which authenticates using basic authentication (username/password).
 * <b>Methods</b>: doRequest, attachSecurityInfo <br>
 * 
 */
public class BasicAuthClient extends HttpClient {

    protected static final String SECURITY_ENDPOINT_DOES_NOT_EXIST = "SECURITY_ENDPOINT_DOES_NOT_EXIST";
    protected static final String SECURITY_TOKEN_PARAM_KEY = "key";
    private static final String SECURITY_TOKEN_URL = "/security/authorize";
    protected static final String SECURITY_TOKEN_KEY = "SecurityToken";
    protected String securityToken;
    protected Credentials credentials;

    /**
     * <b>Description:</b> Construct a new client.
     * 
     */
    public BasicAuthClient(URI server, String userName, String password) {
        super(server);
        credentials = setClientCredentials(server, userName, password);
    }

    /**
     * <b>Description:</b> Execute a request against the WSAPI
     *
     * 
     */
    @Override
    protected String doRequest(HttpRequestBase request) throws IOException {
        if(!request.getMethod().equals(HttpGet.METHOD_NAME) &&
                !this.getWsapiVersion().matches("^1[.]\\d+")) {
            try {
                attachSecurityInfo(request);
            } catch (URISyntaxException e) {
                throw new IOException("Unable to build URI with security token", e);
            }
        }
        return super.doRequest(request);
    }

    /**
     * <b>Description:</b> Attach the security token parameter to the request.
     *
    
     *
     *
     */
    @SuppressWarnings("deprecation")
	protected void attachSecurityInfo(HttpRequestBase request) throws IOException, URISyntaxException {
        if (!SECURITY_ENDPOINT_DOES_NOT_EXIST.equals(securityToken)) {
            try {
                if (securityToken == null) {
                    HttpGet httpGet = new HttpGet(getWsapiUrl() + SECURITY_TOKEN_URL);
                    httpGet.addHeader(BasicScheme.authenticate(credentials, "utf-8", false));
                    GetResponse getResponse = new GetResponse(doRequest(httpGet));
                    JsonObject operationResult = getResponse.getObject();
                    JsonPrimitive securityTokenPrimitive = operationResult.getAsJsonPrimitive(SECURITY_TOKEN_KEY);
                    securityToken = securityTokenPrimitive.getAsString();
                }
                request.setURI(new URIBuilder(request.getURI()).addParameter(SECURITY_TOKEN_PARAM_KEY, securityToken).build());
            } catch (IOException e) {
            	
            	Log.info(e);
                //swallow the exception in this case as url does not exist indicates running and old version of
                //ALM without the security endpoint
                securityToken = SECURITY_ENDPOINT_DOES_NOT_EXIST;
            }
        }
    }
}
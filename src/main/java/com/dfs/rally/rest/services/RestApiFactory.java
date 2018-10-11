package com.dfs.rally.rest.services;

import java.net.URI;
import java.net.URISyntaxException;

import com.dfs.rally.rest.RallyRestApi;
/**
 * <b>Description:</b>  <br>
 * <b>Methods</b>:  getRestApi <br>
 * <b>Date:</b> September 25 2017
 * 
 
 */
public class RestApiFactory {

    //Specify your Rally server
    private static final String SERVER = "https://rally1.rallydev.com";

    //Specify your WSAPI version
    private static final String WSAPI_VERSION = "v2.0";

    //Specify your Rally username
    private static final String USERNAME = "";

    //Specify your Rally password
    private static final String PASSWORD =null;

//specify your rally api key here 
    private static final String API_KEY = "";

    // proxy set up details::
    private static final String PROXY_SERVER = "";

    //If using an authenticated proxy server specify the username and password
    private static final String PROXY_USERNAME = "";
    private static final String PROXY_PASSWORD = null;
/**
 *  <b>Description:</b>empty constructor
 */
    private RestApiFactory()
    {
    	
    }
    /**
     * 
     * @return restApi 
     * @throws URISyntaxException
     */
    @SuppressWarnings("deprecation")
	public static RallyRestApi getRestApi() throws URISyntaxException {
        RallyRestApi restApi;
        if(API_KEY != null && !"".equals(API_KEY)) {
            restApi = new RallyRestApi(new URI(SERVER), API_KEY);
        } else {
            restApi = new RallyRestApi(new URI(SERVER), USERNAME, PASSWORD);
        }
        if (PROXY_SERVER != null) {
            URI uri = new URI(PROXY_SERVER);
            if (PROXY_USERNAME != null) {
                restApi.setProxy(uri, PROXY_USERNAME, PROXY_PASSWORD);
            } else {
                restApi.setProxy(uri);
            }
        }

        restApi.setWsapiVersion(WSAPI_VERSION);

        return restApi;
    }
}

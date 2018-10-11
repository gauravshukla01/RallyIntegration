package com.dfs.rally.rest.client;

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DecompressingHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import jline.internal.Log;

/**
 * <b>Description:</b> A HttpClient implementation providing connectivity to Rally.  This class does not
 * provide any authentication on its own but instead relies on a concrete subclass to do so.<br>
 * <b>Methods</b>: HttpClient, setProxy, setApplicationVendor, setApplicationVersion, 
 *  setApplicationName, getServer, getWsapiVersion, doRequest, executeRequest, doPost, doPut, doDelete, doGet, close, getWsapiUrl <br>

 * 
 
 * @version 1.2.1
 */
@SuppressWarnings("deprecation")
public class HttpClient extends DefaultHttpClient
    implements Closeable {

    protected URI server;
    protected String wsapiVersion = "v2.0";
    protected DecompressingHttpClient client;

    private enum Header {
        Library,
        Name,
        Vendor,
        Version
    }

    private  EnumMap<Header, String> headers = new  EnumMap<Header, String>(Header.class) {
        
		private static final long serialVersionUID = -7406975927745868962L;

		{
			put(Header.Library, "Rally Rest API for Java v2.2.1");
			put(Header.Name, "Rally Rest API for Java");
			put(Header.Vendor, "Rally Software, Inc.");
			put(Header.Version, "2.2.1");
        }
    };

    /**
     * 
     * @param server the server to connect to
     */
	protected HttpClient(URI server) {
        this.server = server;
        client = new DecompressingHttpClient(this);
    }

	/**
     * <b>Description:</b> Set the unauthenticated proxy server to use.  By default no proxy is configured.
     *
     *
     */
    public void setProxy(URI proxy) {
        this.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, new HttpHost(proxy.getHost(), proxy.getPort(), proxy.getScheme()));
    }

    /**
     * <b>Description:</b> Set the authenticated proxy server to use.  By default no proxy is configured.
     *
  
     */
    public void setProxy(URI proxy, String userName, String password) {
        setProxy(proxy);
        setClientCredentials(proxy, userName, password);
    }

    /**
     * <b>Description:</b> Set the value of the X-RallyIntegrationVendor header included on all requests.
     * This should be set to your company name.
     *
    
     */
    public void setApplicationVendor(String value) {
        headers.put(Header.Vendor, value);
    }

    /**
     * <b>Description:</b> Set the value of the X-RallyIntegrationVersion header included on all requests.
     * This should be set to the version of your application.
     *
   
     */
    public void setApplicationVersion(String value) {
        headers.put(Header.Version, value);
    }

    /**
     * <b>Description:</b> Set the value of the X-RallyIntegrationName header included on all requests.
     * This should be set to the name of your application.
     *
    
     */
    public void setApplicationName(String value) {
        headers.put(Header.Name, value);
    }

    /**
     * <b>Description:</b> Get the current server being targeted.
     *
   
     */
    public String getServer() {
        return server.toString();
    }

    /**
     * <b>Description:</b> Get the current version of the WSAPI being targeted.
     *
    
     */
    public String getWsapiVersion() {
        return wsapiVersion;
    }

    /**
     * <b>Description:</b> Set the current version of the WSAPI being targeted.
     *
     * @param wsapiVersion the new version, e.g. {@code "1.30"}
     */
    public void setWsapiVersion(String wsapiVersion) {
        this.wsapiVersion = wsapiVersion;
    }

    /**
     * <b>Description:</b> Execute a request against the WSAPI
     *
  
     */
    protected String doRequest(HttpRequestBase request) throws IOException {
        for (Map.Entry<Header, String> header : headers.entrySet()) {
            request.setHeader("X-RallyIntegration" + header.getKey().name(), header.getValue());
        }

        return this.executeRequest(request);
    }

    /**
     * <b>Description:</b> Execute a request against the WSAPI
     *
    
     */
    protected String executeRequest(HttpRequestBase request) throws IOException {
        HttpResponse response = client.execute(request);
        Log.info("Request is: "+request.toString());
        
        Log.info("Response is: "+response.toString());
        HttpEntity entity = response.getEntity();
        if (response.getStatusLine().getStatusCode() == 200) {
            return EntityUtils.toString(entity, "utf-8");
        } else {
            EntityUtils.consumeQuietly(entity);
            throw new IOException(response.getStatusLine().toString());
        }
    }

    /**
     * <b>Description:</b> Perform a post against the WSAPI
     *
   
     */
    public String doPost(String url, String body) throws IOException {
        HttpPost httpPost = new HttpPost(getWsapiUrl() + url);
        httpPost.setEntity(new StringEntity(body, "utf-8"));
        return doRequest(httpPost);
    }


    /**
     * <b>Description:</b> Perform a put against the WSAPI
     *
    
     */
    public String doPut(String url, String body) throws IOException {
        HttpPut httpPut = new HttpPut(getWsapiUrl() + url);
        httpPut.setEntity(new StringEntity(body, "utf-8"));
        return doRequest(httpPut);
    }

    /**
     * <b>Description:</b> Perform a delete against the WSAPI
     *
   
     */
    public String doDelete(String url) throws IOException {
        HttpDelete httpDelete = new HttpDelete(getWsapiUrl() + url);
        return doRequest(httpDelete);
    }

    /**
     * <b>Description:</b> Perform a get against the WSAPI
     *
   
     */
    public String doGet(String url) throws IOException {
        HttpGet httpGet = new HttpGet(getWsapiUrl() + url);
        return doRequest(httpGet);
    }

    /**
     * <b>Description:</b> Release all resources associated with this instance.
     */
	@Override
	public void close() {
        client.getConnectionManager().shutdown();
    }

	protected Credentials setClientCredentials(URI server, String userName, String password) {
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(userName, password);
        this.getCredentialsProvider().setCredentials(new AuthScope(server.getHost(), server.getPort()), credentials);
        return credentials;
    }

    /**
     * <b>Description:</b> Get the WSAPI base url based on the current server and WSAPI version
     *
   
     */
    public String getWsapiUrl() {
        return getServer() + "/slm/webservice/" + getWsapiVersion();
    }
}

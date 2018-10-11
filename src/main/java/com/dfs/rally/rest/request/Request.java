package com.dfs.rally.rest.request;

import com.google.gson.GsonBuilder;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>Description:</b><p>Base class for all WSAPI requests.</p>  
 * Subclasses classes should provide an implementation of {@link com.dfs.rally.rest.request.Request#toUrl}<br>
 * <b>Methods:</b> setParams, addParam, getGsonBuilder, setGsonBuilder, toUrl <br>
 * <b>Date:</b>September 07 2017
 * 

 * @version 1.2.1
 */
public abstract class Request {
    
    private List<NameValuePair> params = new ArrayList<>();

	/**
     * <b>Description:</b> Gson Builder used for JSON serialization in this request.
     */
    protected GsonBuilder gsonBuilder;

    /**
     * Create a new request.
     */
    public Request() {
        this.gsonBuilder = new GsonBuilder().serializeNulls();
    }

    /**
     * <b>Description:</b> Get the list of additional parameters included in this request.
     * 
     * @return The list of additional parameters
     */
    public List<NameValuePair> getParams() {
        return params;
    }

    /**
     *<b>Description:</b> Set the list of additional parameters included in this request.
     * 
     * @param params The list of additional parameters
     */
    public void setParams(List<NameValuePair> params) {
        this.params = params;
    }

    /**
     *<b>Description:</b> Add the specified parameter to this request.
     * 
     * @param name the parameter name
     * @param value the parameter value
     */
    public void addParam(String name, String value) {
        getParams().add(new BasicNameValuePair(name, value));
    }

    /**
     *<b>Description:</b> Get the Gson Builder used for JSON serialization in this request.
     *
     * @return The Gson Builder used for JSON serialization
     */
    public GsonBuilder getGsonBuilder() {
        return gsonBuilder;
    }

    /**
     *<b>Description:</b> Set the Gson Builder used for JSON serialization in this request.
     *
     * @param gsonBuilder The Gson Builder used for JSON serialization
     */
    public void setGsonBuilder(GsonBuilder gsonBuilder) {
        this.gsonBuilder = gsonBuilder;
    }

    /**
     *<b>Description:</b> <p>Convert this request into a url compatible with the WSAPI.</p>
     * Must be implemented by subclasses.
     * 
     * @return the url representing this request.
     */
    public abstract String toUrl();
}

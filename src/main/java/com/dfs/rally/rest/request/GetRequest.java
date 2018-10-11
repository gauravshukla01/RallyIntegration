package com.dfs.rally.rest.request;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import com.dfs.rally.rest.util.Fetch;
import com.dfs.rally.rest.util.Ref;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>Description:</b> Represents a WSAPI request to retrieve a specific object.<br>
 * <b>Methods:</b> getFetch, setFetch <br>
 * <b>Date:</b> September 05 2017
 * 

 */
public class GetRequest extends Request {

    private String ref;
    private Fetch fetch = new Fetch();

    /**
     * <b>Description:</b> Create a new get request for the specified object.
     *
     * @param ref the ref of the WSAPI object to be retrieved.  May be absolute or relative, e.g. "/defect/12345"
     *            May also be "/user" or "/subscription" to get the current instances
     */
    public GetRequest(String ref) {
        this.ref = ref;
    }

    /**
     * <b>Description:</b> <p>Get the current list of fields to be returned on the retrieved object.</p>
     * By default all fields will be returned in the response (fetch=true).
     *
     * @return the current list of fields.
     */
    public Fetch getFetch() {
        return fetch;
    }

    /**
     * <b>Description:</b> Set the current list of fields to be returned on the retrieved object.
     *
     * @param fetch the list of fields to be returned.
     */
    public void setFetch(Fetch fetch) {
        this.fetch = fetch;
    }

    /**
     * <b>Description:</b> <p>Convert this request into a url compatible with the WSAPI.</p>
     * The current fetch and any other parameters will be included.
     *
     * @return the url representing this request.
     */
    @Override
    public String toUrl() {
        List<NameValuePair> params = new ArrayList<>(getParams());
        params.add(new BasicNameValuePair("fetch", fetch.toString()));
        return String.format("%s.js?%s", getEndpoint(),
                URLEncodedUtils.format(params, "utf-8"));
    }
/**
 * 
 * @return 
 */
    protected String getEndpoint() {
        String endpoint = ref.toLowerCase();
        if (Ref.isRef(endpoint)) {
            endpoint = Ref.getRelativeRef(endpoint);
        } else if (endpoint.contains("user")) {
            endpoint = "/user";
        } else if (endpoint.contains("subscription")) {
            endpoint = "/subscription";
        }

        return endpoint;
    }
}

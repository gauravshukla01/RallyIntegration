package com.dfs.rally.rest.request;

import com.dfs.rally.rest.util.Fetch;
import com.dfs.rally.rest.util.Ref;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>Description:</b>  Represents a WSAPI request to update a collection.
 * <b>Methods:</b> CollectionUpdateRequest, getBody, getFetch, setFetch, toUrl <br>
 * 
 */
public class CollectionUpdateRequest extends Request {

    private String ref;
    private JsonArray items;
    private boolean adding;
    private Fetch fetch = new Fetch();

    /**
     * <b>Description:</b> Create a new update request for the specified collection and values.<br>
     * Note that this class is only usable with WSAPI versions 2.0 and above.
     *
     * @param collection the collection to be updated
     * @param items the items to be added or removed
     * @param add true if adding, false if removing
     */
    public CollectionUpdateRequest(JsonObject collection, JsonArray items, boolean add) {
        this(collection.get("_ref").getAsString(), items, add);
    }

    /**
     * <b>Description:</b> Create a new update request for the specified collection and values.
     *
     * @param collectionRef the ref of the collection to be updated. May be absolute or relative, e.g. "/defect/12345/tags"
     * @param items the items to be added or removed
     * @param adding true if adding, false if removing
     */
    public CollectionUpdateRequest(String collectionRef, JsonArray items, boolean adding) {
        this.ref = collectionRef;
        this.items = items;
        this.adding = adding;
    }

    /**
     * <b>Description:</b> Get the JSON encoded string representation of the object to be updated.
     *
     * @return the JSON encoded object
     */
    public String getBody() {
        JsonObject wrapper = new JsonObject();
        wrapper.add("CollectionItems", items);
        return gsonBuilder.create().toJson(wrapper);
    }

    /**
     * <b>Description:</b> <p>Get the current list of fields to be returned on the updated object.</p>
     * By default all fields will be returned in the response (fetch=true).
     *
     * @return the current list of fields.
     */
    public Fetch getFetch() {
        return fetch;
    }

    /**
     * <b>Description:</b> Set the current list of fields to be returned on the updated object.
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

        params.add(new BasicNameValuePair("fetch", getFetch().toString()));

        return String.format("%s/%s.js?%s", Ref.getRelativeRef(ref),
                adding ? "add" : "remove",
                URLEncodedUtils.format(params, "utf-8"));
    }
}

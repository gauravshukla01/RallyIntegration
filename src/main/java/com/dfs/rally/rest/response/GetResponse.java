package com.dfs.rally.rest.response;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Map;

/**
 * <b>Description:</b> Represents a WSAPI response from retrieving a single object.<br>
 * <b>Methods:</b> getRoot, getObject <br>
 
 * @version 1.2.1
 */
public class GetResponse extends Response {

    /**
     *<b>Description:</b> Create a new get response from the specified JSON encoded string.
     *
     * @param getResponse the JSON encoded string
     */
    public GetResponse(String getResponse) {
        super(getResponse);
    }

    /**
     *<b>Description:</b> Get the name of the root JSON result
     *
     * @return the root element name
     */
    @Override
    protected String getRoot() {
        JsonObject result = ((JsonObject) new JsonParser().parse(raw));
        String root = null;
        for(Map.Entry<String, JsonElement> member : result.entrySet()) {
            root = member.getKey();
        }
        return root;
    }

    /**
     * <b>Description:</b> Get the retrieved object.
     * <p>Returns null if the operation was not successful</p> 
     * @return the retrieved object
     */
    public JsonObject getObject() {
        return wasSuccessful() ? result : null;
    }
}

package com.dfs.rally.rest.response;

import com.google.gson.JsonObject;

/**
 * <b>Description:</b> Represents a WSAPI response from updating an object.<br>
 * <b>Methods:</b> getRoot, getObject <br>

 * @version 1.2.1
 */
public class UpdateResponse extends Response {

    /**
     *<b>Description:</b> Create a new update response from the specified JSON encoded string.
     *
     * @param updateResponse the JSON encoded string
     */
    public UpdateResponse(String updateResponse) {
        super(updateResponse);
    }

    /**
     *<b>Description:</b> Get the name of the root JSON result
     *
     * @return the root element name
     */
    @Override
    protected String getRoot() {
        return "OperationResult";
    }

    /**
     *<b>Description:</b> Get the updated object.
     * <p>Returns null if the operation was not successful</p>
     * @return the updated object
     */
    public JsonObject getObject() {
        return wasSuccessful() ? result.getAsJsonObject("Object") : null;
    }
}

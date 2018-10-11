package com.dfs.rally.rest.response;

import com.google.gson.JsonObject;

/**
 * <b>Description:</b> Represents a WSAPI response from creating an object<br>
 * <b>Methods:</b> getRoot, getObject <br>
  * @version 1.2.1
 */
public class CreateResponse extends Response {

    /**
     *<b>Description:</b> Create a new create response from the specified JSON encoded string.
     *
     * @param createResponse the JSON encoded string
     */
    public CreateResponse(String createResponse) {
        super(createResponse);
    }

    /**
     *<b>Description:</b> Get the name of the root JSON result
     *
     * @return the root element name
     */
    @Override
    protected String getRoot() {
        return "CreateResult";
    }

    /**
     *<b>Description:</b> Get the created object.
     * <p>Returns null if the operation was not successful</p>
     * @return the created object
     */
    public JsonObject getObject() {
        return wasSuccessful() ? result.getAsJsonObject("Object") : null;
    }
}

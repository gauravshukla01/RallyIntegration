package com.dfs.rally.rest.response;

import com.google.gson.JsonArray;

/**
 * <b>Description:</b> Represents a WSAPI response from updating a collection.<br>
 * <b>Methods:</b> getRoot, getResults <br>
* 
 
 * @version 1.2.1
 */
public class CollectionUpdateResponse extends Response {

    /**
     *<b>Description:</b> Create a new collection update response from the specified JSON encoded string.
     * Note that this class is only usable with WSAPI versions 2.0 and above.
     *
     * @param updateResponse the JSON encoded string
     */
    public CollectionUpdateResponse(String updateResponse) {
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
     *<b>Description:</b> Get the results of the collection update
     * @return the results
     */
    public JsonArray getResults() {
        return result.getAsJsonArray("Results");
    }
}

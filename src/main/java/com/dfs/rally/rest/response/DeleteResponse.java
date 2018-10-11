package com.dfs.rally.rest.response;

/**
 * <b>Description:</b> Represents a WSAPI response from deleting an object.<br>
 * <b>Methods:</b> getRoot <br>

 * @version 1.2.1
 */
public class DeleteResponse extends Response {

    /**
     *<b>Description:</b> Create a new delete response from the specified JSON encoded string.
     *
     * @param deleteResponse the JSON encoded string
     */
    public DeleteResponse(String deleteResponse) {
        super(deleteResponse);
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
}

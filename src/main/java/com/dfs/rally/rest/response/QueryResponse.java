package com.dfs.rally.rest.response;

import com.google.gson.JsonArray;

/**
 * <b>Description:</b> Represents a WSAPI response from querying for objects.<br>
 * <b>Methods:</b> getRoot, getTotalResultCount, getResults, getPageSize, getStart <br>
 * <b>Date:</b> August 24 2017

 * @version 1.2.1
 */
public class QueryResponse extends Response {

    /**
     *<b>Description:</b> Create a new query response from the specified JSON encoded string.
     *
     * @param queryResponse the JSON encoded string
     */
    public QueryResponse(String queryResponse) {
        super(queryResponse);
    }

    /**
     *<b>Description:</b> Get the name of the root JSON result
     *
     * @return the root element name
     */
    @Override
    protected String getRoot() {
        return "QueryResult";
    }

    /**
     *<b>Description:</b> Get the total number of objects that matched the query
     *
     * @return the total number of objects
     */
    public int getTotalResultCount() {
        return result.get("TotalResultCount").getAsInt();
    }

    /**
     * <b>Description:</b> Get the results of the query
     * <p>Depending on the limit of the original request this may include one or more pages.</p>
     *
     * @return the results
     */
    public JsonArray getResults() {
        return result.getAsJsonArray("Results");
    }

    /**
     *<b>Description:</b> Get the page size of the results
     *
     * @return the page size
     */
    public int getPageSize() {
        return result.get("PageSize").getAsInt();
    }

    /**
     *<b>Description:</b> Get the start index of the results
     *
     * @return the start index
     */
    public int getStart() {
        return result.get("StartIndex").getAsInt();
    }
}

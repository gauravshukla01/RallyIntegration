package com.dfs.rally.rest.request;

import com.dfs.rally.rest.util.Fetch;
import com.dfs.rally.rest.util.QueryFilter;
import com.dfs.rally.rest.util.Ref;
import com.google.gson.JsonObject;

import jline.internal.Log;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>Description:</b> Represents a WSAPI request to retrieve all objects matching specified criteria.<br>
 * <b>Methods:</b> 
 * getQueryFilter, setQueryFilter, getOrder, setOrder, getWorkspace, setWorkspace, getProject, setProject, isScopedUp, setScopedUp,
 * isScopedDown, setScopedDown, getStart, setStart, getFetch, setFetch, getPageSize, setPageSize, getLimit, setLimit, clone, toUrl, 
 * getTypeEndpoint <br>
 * <b>Date:</b> September 06 2017
 * 

 * @version 1.2.1
 */
public class QueryRequest extends Request implements Cloneable {

    private String type;
    private JsonObject collection;

    private Fetch fetch = new Fetch();
    private String order = "ObjectID";
    private QueryFilter queryFilter = null;

    private int pageSize = 200;
    private int limit = 0;
    private int start = 1;

    private String workspace = "";
    private String project = "";
    private boolean scopedUp = false;
    private boolean scopedDown = true;

    /**
     * <b>Description:</b> Create a new query request for the specified type.
     *
     * @param type The WSAPI type to be queried, e.g. Defect
     */
    public QueryRequest(String type) {
        this.type = type;
    }

    /**
     * <b>Description:</b> Create a new query request for the specified collection.
     * Only supported in WSAPI v2.0 and above.
     *
     * @param collection The collection to query.  Should have a _ref property.
     */
    public QueryRequest(JsonObject collection) {
        this.collection = collection;
    }

    /**
     * <b>Description:</b> Get the filter by which the result set will be narrowed down.
     *
     * @return the filter
     */
    public QueryFilter getQueryFilter() {
        return queryFilter;
    }

    /**
     * <b>Description:</b> Set a filter by which the result set will be narrowed down.
     *
     * @param queryFilter the filter
     */
    public void setQueryFilter(QueryFilter queryFilter) {
        this.queryFilter = queryFilter;
    }

    /**
     * <b>Description:</b> Set the order by which the result set will be sorted.
     * <p>The default is ObjectID ASC.</p>
     *
     * @return the order
     */
    public String getOrder() {
        return order;
    }

    /**
     * <b>Description:</b> Get the order by which the result set will be sorted.
     *
     * @param order the order
     */
    public void setOrder(String order) {
        this.order = order;
    }

    /**
     * <b>Description:</b> Get the workspace which the result set should be scoped to.
     *
     * @return the project
     */
    public String getWorkspace() {
        return workspace;
    }

    /**
     * <b>Description:</b> <p>Specify the workspace which the result set should be scoped to.</p>
     * The default is the user's default workspace.
     *
     * @param workspaceRef the ref of the workspace to scope to.  May be an absolute or relative ref, e.g. /workspace/1234
     */
    public void setWorkspace(String workspaceRef) {
        this.workspace = workspaceRef;
    }

    /**
     * <b>Description:</b> Get the project which the result set should be scoped to.
     *
     * @return the project
     */
    public String getProject() {
        return project;
    }

    /**
     * <b>Description:</b> <p>Specify the project which the result set should be scoped to.</p>
     * The default is the user's default project.
     * Specifying null will cause the result to be scoped to the entire specified workspace.
     *
     * @param projectRef the ref of the project to scope to.  May be null or an absolute or relative ref, e.g. /project/1234
     */
    public void setProject(String projectRef) {
        this.project = projectRef;
    }

    /**
     * <b>Description:</b> If a project has been specified, get whether to include matching objects in parent projects in the result set.
     *
     * @return whether to include matching objects in parent projects.
     */
    public boolean isScopedUp() {
        return scopedUp;
    }

    /**
     * <b>Description:</b> <p>If a project has been specified, set whether to include matching objects in parent projects in the result set.</p>
     * Defaults to false.
     *
     * @param scopeUp whether to include matching objects in parent projects
     */
    public void setScopedUp(boolean scopeUp) {
        this.scopedUp = scopeUp;
    }

    /**
     * <b>Description:</b> If a project has been specified, get whether to include matching objects in child projects in the result set.
     *
     * @return whether to include matching objects in child projects.
     */
    public boolean isScopedDown() {
        return scopedDown;
    }

    /**
     * <b>Description:</b> <p>If a project has been specified, set whether to include matching objects in child projects in the result set.</p>
     * Defaults to true.
     *
     * @param scopeDown whether to include matching objects in child projects
     */
    public void setScopedDown(boolean scopeDown) {
        this.scopedDown = scopeDown;
    }

    /**
     * <b>Description:</b> Get the start index of the result set.
     *
     * @return the start index
     */
    public int getStart() {
        return start;
    }

    /**
     * <b>Description:</b> Set the 1-based start index of the result set.
     * The default is 1.
     *
     * @param start the start index
     */
    public void setStart(int start) {
        this.start = start;
    }

    /**
     * <b>Description:</b> <p>Get the current list of fields to be returned on the matching objects.</p>
     * By default all fields will be returned in the response (fetch=true).
     *
     * @return the current list of fields.
     */
    public Fetch getFetch() {
        return fetch;
    }

    /**
     * <b>Description:</b> Set the current list of fields to be returned on the matching objects.
     *
     * @param fetch the list of fields to be returned.
     */
    public void setFetch(Fetch fetch) {
        this.fetch = fetch;
    }

    /**
     * <b>Description:</b> Get the page size of the result set.
     *
     * @return the page size
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * <b>Description:</b> <p>Set the page size of the result set.</p>
     * The default is 200.
     *
     * @param pageSize the new page size.  Must be between 1 and 200 inclusive.
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * <b>Description:</b> Get the maximum number of records to be returned from the query.
     *
     * @return the maximum number of records
     */
    public int getLimit() {
        return limit;
    }

    /**
     * <b>Description:</b> <p>Set the maximum number of records to be returned from the query.</p>
     * If not set only one page of data will be returned by {@link com.dfs.rally.rest.RallyRestApi#query}
     *
     * @param limit the maximum number of records to be returned
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * <b>Description:</b> Clone this request.
     *
     * @return the cloned instance of this request.
     */
    @Override
    public QueryRequest clone() {
        try {
            return (QueryRequest) super.clone();
        } catch (CloneNotSupportedException c) {
        	Log.info(c);
            return null;
        }
    }

    /**
     * <b>Description:</b> <p>Convert this request into a url compatible with the WSAPI.</p>
     * The current criteria set on this request and any other parameters will be included.
     *
     * @return the url representing this request.
     */
    @Override
    public String toUrl() {
        List<NameValuePair> params = new ArrayList<>(getParams());
      
        params.add(new BasicNameValuePair("start", Integer.toString(getStart())));
        params.add(new BasicNameValuePair("pagesize", Integer.toString(getPageSize())));
        params.add(new BasicNameValuePair("fetch", fetch.toString()));

        String order1 = getOrder();
        if (!order1.contains("ObjectID")) {
            order1 += ",ObjectID";
        }
        params.add(new BasicNameValuePair("order", order1));
        if (getQueryFilter() != null) {
            params.add(new BasicNameValuePair("query", getQueryFilter().toString()));
        }

        if (getWorkspace() != null && getWorkspace().length() > 0) {
            params.add(new BasicNameValuePair("workspace", Ref.getRelativeRef(getWorkspace())));
        }

        if (getProject() == null) {
            params.add(new BasicNameValuePair("project", "null"));
        } else if (getProject().length() > 0) {
            params.add(new BasicNameValuePair("project", getProject()));
            params.add(new BasicNameValuePair("projectScopeUp", Boolean.toString(isScopedUp())));
            params.add(new BasicNameValuePair("projectScopeDown", Boolean.toString(isScopedDown())));
        }

        return (this.type != null ? getTypeEndpoint() :
                Ref.getRelativeRef(collection.get("_ref").getAsString())) +
                "?" + URLEncodedUtils.format(params, "utf-8");
    }

    protected String getTypeEndpoint() {
        String typeEndpoint = type.toLowerCase().replaceAll(" ", "");
        if ("subscription".equals(typeEndpoint) || "user".equals(typeEndpoint)) {
            typeEndpoint += "s";
        }
        return "/" + typeEndpoint + ".js";
    }
}
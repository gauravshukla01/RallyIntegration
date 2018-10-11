package com.dfs.rally.objectid;

import java.io.IOException;
import java.net.URISyntaxException;

import com.dfs.rally.rest.RallyRestApi;
import com.dfs.rally.rest.request.QueryRequest;
import com.dfs.rally.rest.response.QueryResponse;
import com.dfs.rally.rest.services.RestApiFactory;
import com.dfs.rally.rest.util.QueryFilter;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import jline.internal.Log;

/**
 * <b>Description:</b> This class performs action to get Object ID for TestSet and TestCase<br>
 * <b>Methods</b>: testSetObjectID, testCaseObjectID <br>
 * <b>Date:</b> August 24 2017
 * 
 *
 * @version 1.2.1
 */
public class TestObjectID {
	
	/**
	 * <b>Description:</b> empty constructor
	 */
	private TestObjectID()
	{
		
	}
	
	/**
	 * <b>Description:</b> Get the Object ID of the TestSet
	 * @param testsetName name of the testSet as a String
	 * @return testSet Object ID as a String
	 */
	public static String testSetObjectID(String testsetName) 
	
	{
		RallyRestApi restApi=null;
		try {
			restApi = RestApiFactory.getRestApi();
		} catch (URISyntaxException e) {
			Log.info(e);
		}
        
		String testSetName = testsetName.trim();

		Log.info("Querying for top 5 Iterations..."+testSetName);

            QueryRequest testSet = new QueryRequest("testset");
            testSet.setQueryFilter(new QueryFilter("name", "=", testSetName));
           
            

            //Return up to 5, 1 per page
            testSet.setPageSize(1);
            testSet.setLimit(1);

            QueryResponse queryResponse=null;
			try {
				queryResponse = restApi.query(testSet);
			} catch (IOException e) {
				Log.info(e);
			}
            Log.info(String.format("\nTotal results: %d", queryResponse.getTotalResultCount()));
            if (queryResponse.wasSuccessful()) {
            	Log.info(String.format("\nTotal results: %d", queryResponse.getTotalResultCount()));
                for (JsonElement result : queryResponse.getResults()) {
                    JsonObject testsetresults = result.getAsJsonObject();
                    Log.info("Get complete response: "+testsetresults.toString());
                    Log.info(testsetresults.get("_refObjectName").getAsString());
                    Log.info(testsetresults.get("_ref").getAsString());
                    
                    return (testsetresults.get("ObjectID").getAsString());

                }
            } else {
                Log.error("The following errors occurred: ");
                for (String err : queryResponse.getErrors()) {
                	Log.error("\t" + err);
                }
            }
        	return "null";
	}
	
	/**
	 * <b>Description:</b> Get the Object ID of the TestCase
	 * @param testcaseName name of the testCase as a String
	 * @return testCase Object ID as a String
	 */
	public static String testCaseObjectID(String testcaseName) 
	
	{
		RallyRestApi restApi=null;
		try {
			restApi = RestApiFactory.getRestApi();
		} catch (URISyntaxException e) {
			
			Log.info(e);
		}
      
      String testCaseName = testcaseName;


         Log.info("Querying for top 5 Iterations...");

          QueryRequest testCase = new QueryRequest("testcase");

          testCase.setQueryFilter(new QueryFilter("name", "=", testCaseName));
          

          //Return up to 5, 1 per page
          testCase.setPageSize(1);
          testCase.setLimit(1);

          QueryResponse queryResponse=null;
		try {
			queryResponse = restApi.query(testCase);
		} catch (IOException e) {
		
			Log.info(e);
		}
          if (queryResponse.wasSuccessful()) {
        	  Log.info(String.format("\nTotal results: %d", queryResponse.getTotalResultCount()));
              for (JsonElement result : queryResponse.getResults()) {
                  JsonObject testsetresults = result.getAsJsonObject();
                  Log.info("Get complete response: "+testsetresults.toString());
                  Log.info(testsetresults.get("_refObjectName").getAsString());
                  Log.info(testsetresults.get("_ref").getAsString());
                
                  return (testsetresults.get("ObjectID").getAsString());
              }
          } else {
        	  Log.error("The following errors occurred: ");
              for (String err : queryResponse.getErrors()) {
            	  Log.error("\t" + err);
              }
          }
      	return "null";
	}
	
}


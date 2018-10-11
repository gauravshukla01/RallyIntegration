package com.dfs.rally.rest.services;

import java.io.IOException;
import java.net.URISyntaxException;

import com.dfs.almrally.feature.pojo.Feature;
//import com.dfs.rally.rest.Constants;
import com.dfs.rally.rest.RallyRestApi;
import com.dfs.rally.rest.request.CreateRequest;
import com.dfs.rally.rest.response.CreateResponse;
import com.dfs.util.Config;
import com.google.gson.JsonObject;

import jline.internal.Log;

/**
 * <b>Description:</b> This class creates a Test Folder in Test Plan <br>
 * <b>Methods</b>:  createTestPlanFolder <br>
 * <b>Date:</b> September 25 2017
 * 

 */
public class TestPlanFolder {
	static Config config;

	
	/**
	 * 
	 */
	private TestPlanFolder(){
		//empty constructor
	}
	/**
	 * 
	 * @param f feature file path as a String
	 * @return object ID of a new Folder in Test Plan
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	 public static String createTestPlanFolder(Feature f) throws URISyntaxException, IOException {

      RallyRestApi restApi = RestApiFactory.getRestApi();
  
      try {
       
          
        //Create a Test Folder in Test Plan
          Log.info("Creating Test Folder...");
          
          
          JsonObject newTestFolder = new JsonObject();
          newTestFolder.addProperty("Name", f.name);
          newTestFolder.addProperty("FormattedID", true);
          newTestFolder.addProperty("Project",  config.get("rally", "ProjectID").trim());	
      	
          CreateRequest createRequest = new CreateRequest("TestFolder", newTestFolder);
          
          Log.info("------------------BEGIN :  TEST FOLDER REQUEST BODY-------------------------");
          Log.info( createRequest.getBody() );
                    
          CreateResponse createResponse = restApi.create(createRequest);
          Log.info("Json object: "+createResponse.getObject().get("ObjectID").getAsNumber());
         
          String[] errorStrings = createResponse.getErrors();
          if(errorStrings.length>0){
                for(int i=0;i <=errorStrings.length; i++){
                	Log.info( errorStrings[i] );
                }
         }
          
          Log.info(String.format("Created %s", createResponse.getObject().get("_ref").getAsString()));
          Log.info("------------------END :  TEST FOLDER REQUEST BODY-------------------------");
          return (createResponse.getObject().get("ObjectID").getAsString());
          
      }finally {
       //Release all resources
       restApi.close();
   }
  }


};
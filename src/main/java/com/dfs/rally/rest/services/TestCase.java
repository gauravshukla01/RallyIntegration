package com.dfs.rally.rest.services;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

//import com.dfs.rally.rest.Constants;
import com.dfs.rally.rest.RallyRestApi;
import com.dfs.rally.rest.request.CreateRequest;
import com.dfs.rally.rest.response.CreateResponse;
import com.dfs.util.Config;
import com.google.gson.JsonObject;
import com.dfs.almrally.feature.pojo.Element;
import com.dfs.almrally.feature.pojo.Example;
import com.dfs.almrally.feature.pojo.Row;
import com.dfs.exceptions.ScenarioNameNotFound;

import jline.internal.Log;
/**
 * <b>Description:</b> This class creates a Test Case <br>
 * <b>Methods</b>:  createTestCase <br>
 * <b>Date:</b> August 24 2017
 * 

 */
public class TestCase {
	static Config config;

	/**
	 * <b>Description:</b> empty constructor
	 */
			private TestCase()
			{
				
			}
			/**
			 * 
			 * @param element Json property
			 * @param testPlanObj Object of a test plan folder
			 * @param tags2 Feature file tags
			 * @throws IOException
			 * @throws URISyntaxException
			 */
			public static void createTestCase(Element element, String testPlanObj, String tags2) throws URISyntaxException
	    	   
	    	   {
	              //Create and configure a new instance of RallyRestApi
	           //(Server, username, password and proxy settings configured in Factory)
				
	           
	       
	           try (RallyRestApi restApi = RestApiFactory.getRestApi()){
	                 	   
	             //Create a Test Case with Parameters
	               Log.info("Creating TestCase...");
	            
	               JsonObject newTestCase = new JsonObject();
	               if(element.name.length()>0)
            		   newTestCase.addProperty("Name", element.name);
	               else if(element.description.length()>0)
            		   newTestCase.addProperty("Name", element.description);
	               else 
	            	   throw new ScenarioNameNotFound("Please enter the Scenario name");
	               newTestCase.addProperty("DefectStatus", "NONE");
	               newTestCase.addProperty("Method", "Automated");
	               newTestCase.addProperty("TestFolder", testPlanObj);
	               newTestCase.addProperty("Project", config.get("rally", "ProjectID").trim());	//Dev Workspace
	               
            	   //Parsing through tags
	               
	               StringBuilder tagBuilder = new StringBuilder();      	   
	               for (int tagcnt = 0; tagcnt < element.tags.size(); tagcnt++)
	               {
	            	   tagBuilder.append(element.tags.get(tagcnt).name);	
	            	   
	               }	               
	               String tags1 = tagBuilder.toString();	     
	               String tags=tags1+"\r\n"+tags2;
	               Log.info("Total tags: "+tags);	               
	               newTestCase.addProperty("Description", tags);

               
	               //Parsing through the examples and retrieve the data. Applicable only for Scenario Outline              
	               StringBuilder  sb = new StringBuilder();
        		   
	               if ("Scenario Outline".equalsIgnoreCase(element.keyword) && element.examples.size() >0)
	               {

	            	   //if (element.examples.size() >0){
	            		   Example example = element.examples.get(0);
	            		   if (example.rows.size()>0){
	            			   List<Row> rows = example.rows;
	            			   rows.forEach(row -> 
	            				   sb.append( String.join(" | ", row.cells) + "\n" )
	            			   );
	            		   }
	            		   Log.info( sb.toString() );
	            	  // }
	            	      
	             	  newTestCase.addProperty("PreConditions", sb.toString());  
	             } 
	               
	               CreateRequest createRequest = new CreateRequest("TestCase", newTestCase);
	               
	               Log.info("------------------BEGIN :  REQUEST BODY-------------------------");
	               Log.info( createRequest.getBody() );
	               Log.info("------------------END :  REQUEST BODY-------------------------");
	               

	               CreateResponse createResponse = restApi.create(createRequest);
	     	               
	               Log.info(String.format("Created %s", createResponse.getObject().get("_ref").getAsString()));
	               Log.info(String.format("Get test case object ID as Number %s", createResponse.getObject().get("ObjectID").getAsString()));
	               String testCaseObjectID =  createResponse.getObject().get("ObjectID").getAsString();
	               Log.info("Test case Object ID: "+testCaseObjectID);
	               
	               for (int stepscnt = 0; stepscnt < element.steps.size(); stepscnt++)
	               {
	            	   TestCaseSteps.createTestSteps(element.steps.get(stepscnt), testCaseObjectID, (stepscnt+1));
	               }
	               
	               String[] errorStrings = createResponse.getErrors();
	               if(errorStrings.length>0){
	                     for(int i=0;i <=errorStrings.length; i++){
	                    	 Log.info( errorStrings[i] );
	                     }
	              }

	           } catch (IOException e) {
				
				Log.info(e);
			}
	       }
	       
	}
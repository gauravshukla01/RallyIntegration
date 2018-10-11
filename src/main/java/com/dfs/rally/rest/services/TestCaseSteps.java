package com.dfs.rally.rest.services;
import java.io.IOException;
import java.net.URISyntaxException;

import com.dfs.almrally.feature.pojo.Step;
import com.dfs.rally.rest.RallyRestApi;
import com.dfs.rally.rest.request.CreateRequest;
import com.dfs.rally.rest.response.CreateResponse;
import com.google.gson.JsonObject;

import jline.internal.Log;
/**
 * <b>Description:</b> This class creates a Test Case steps <br>
 * <b>Methods</b>:  createTestSteps <br>
 * <b>Date:</b> August 24 2017

 */
public class TestCaseSteps {
/**
 * <b>Description:</b> empty constructor
 */
		private TestCaseSteps()
		{
			
		}
/**
 * 
 * @param step Json property value
 * @param testCaseObjectID ObjectID of a test case
 * @param stepcnt count of a testCase step
 * @throws URISyntaxException
 * @throws IOException
 */
	       public static void createTestSteps(Step step, String testCaseObjectID, int stepcnt) throws URISyntaxException{

	    	   //RallyRestApi restApi = RestApiFactory.getRestApi();
	       
	           try(RallyRestApi restApi = RestApiFactory.getRestApi()) {
	       
	             //Create a Test Case Step for a given test case and workspace
	        	   
	        	   Log.info("Creating TestCaseStep...");
	               
	               
	               JsonObject newTestCaseStep = new JsonObject();
	               newTestCaseStep.addProperty("StepIndex", stepcnt);
	               newTestCaseStep.addProperty("Input", step.keyword);
	               newTestCaseStep.addProperty("ExpectedResult", step.name);
	               newTestCaseStep.addProperty("TestCase", testCaseObjectID);
	               //newTestCaseStep.addProperty("Workspace", "");	//Prod Workspace
	               newTestCaseStep.addProperty("Workspace", "");	//Dev Workspace
	               
	               CreateRequest createRequest = new CreateRequest("TestCaseStep", newTestCaseStep);
	               Log.info("request is" +createRequest.toString() );
	              
	               Log.info("------------------BEGIN :  REQUEST BODY-------------------------");
	               Log.info( createRequest.getBody() );
	               Log.info("------------------END :  REQUEST BODY-------------------------");
	               
	               
	               CreateResponse createResponse = restApi.create(createRequest);
	               String[] errorStrings = createResponse.getErrors();
	               if(errorStrings.length>0){
	                     for(int i=0;i < errorStrings.length; i++){
	                    	 Log.info( errorStrings[i] );
	                     }
	              }
	               
	          // Log.info(String.format("Created %s", createResponse.getObject().get("_ref").getAsString()));
	           } catch (IOException e) {
				
	        	   Log.info(e);
			}
	       }

	}
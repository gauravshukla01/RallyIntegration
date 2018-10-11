package com.dfs.rally.rest.services;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import com.dfs.rally.objectid.TestObjectID;
//import com.dfs.rally.rest.Constants;
import com.dfs.rally.rest.RallyRestApi;
import com.dfs.rally.rest.request.CreateRequest;
import com.dfs.rally.rest.response.CreateResponse;
import com.dfs.util.Config;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.jayway.restassured.path.json.JsonPath;

import complexewallet.pojo.Parentcucumber;
import jline.internal.Log;

/**
 * <b>Description:</b> This class <br>
 * <b>Methods</b>: createTestResults, createTestResult <br>
 * <b>Date:</b> September 25 2017
 * 
 * @author Sai
 * @version 1.2.1
 */
public class CucumberSetResult {
	static Config config;

	

	/**
	 * <b>Description:</b> empty constructor
	 */
	private CucumberSetResult() {

	}

	/**
	 * <b>Description:</b> This method creates the test result
	 */
	// public static void createTestResults (String resultsName, String
	// stepsDescription, String stepsResult, String datefor) throws IOException,
	// URISyntaxException
	public static void createTestResults() {

		try {
			RallyRestApi restApi = RestApiFactory.getRestApi();

			String strJsonFile = "cucumberReport/sample.json";

			ObjectMapper mapper = new ObjectMapper();
			

			JavaType listType = mapper.getTypeFactory().constructCollectionType(List.class, Parentcucumber.class);
			List<Parentcucumber> cucumberReport = mapper.readValue(new File(strJsonFile), listType);

			JsonPath path1 = new JsonPath(mapper.writeValueAsString(cucumberReport));

			List<String> featurename = (path1.getJsonObject("uri"));
			Log.info("size of feature file is" + featurename.size());

			
			
			for (Parentcucumber obj : cucumberReport) {
				for (Object element : obj.elements) {
					
					Map<String,Object> elementDetailedMap = (Map) element;
					List<Map<String,Object>> beforeLists = (List) elementDetailedMap.get("before");
					
					String elementName = (String)elementDetailedMap.get("name");
					String resultStatus = "";
					for (Map<String, Object> beforeMap : beforeLists) {
						
						Map<String,String> resultMap = (Map) beforeMap.get("result");
						resultStatus = resultMap.get("status");
						
						break;
					}
					
					String TestSetObjectID = TestObjectID
							.testSetObjectID(config.get("rally", "TestSet").trim());
					String testCaseID = TestObjectID.testCaseObjectID(elementName);
					if (testCaseID != "null") {
						createTestResult(TestSetObjectID, testCaseID, resultStatus, restApi);
					} else {
						Log.info("No corresponding test case ID " + testCaseID + " found in Rally");
					}
				}
				
			}

		} catch (URISyntaxException | IOException e) {
			Log.info(e);
		}

	}

	/**
	 * <b> Description:</b> Function to create the test results in Rally <br>
	 * 
	 * @param testSetObjectID
	 * @param TestCaseID
	 *            test case ID as a String
	 * @param TestResult
	 *            test case result as a String
	 * @param RestApi
	 *            RestApi variables
	 * @throws IOException
	 */
	// Function to create the test results in Rally
	public static void createTestResult(String testSetObjectID, String TestCaseID, String testResult,
			RallyRestApi RestApi) throws IOException

	{
		// Initialize the variables
		String verdict;
		RallyRestApi restApi = RestApi;

		// Mapping of Serenity Test results status to Rally status

		switch (testResult) {

		case "passed":
			verdict = "Pass";
			break;

		case "failed":
			verdict = "Fail";
			break;

		case "ERROR":
			verdict = "Error";
			break;

		case "skipped":
			verdict = "Inconclusive";
			break;

		default:
			verdict = "Inconclusive";

		}

		String resultsName = "Test Results for " + TestCaseID;
		String testCaseID = TestCaseID;
		Log.info("Creating TestCase Results..." + resultsName);

		// Convert date to ISO date format to post test results time in Rally
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); // Quoted
																		// "Z"
																		// to
																		// indicate
																		// UTC,
																		// no
																		// timezone
																		// offset
		df.setTimeZone(tz);
		String nowAsISO = df.format(new Date());
		Log.info("New date format of ISO 8601: " + nowAsISO);

		JsonObject newTestCaseResult = new JsonObject();
		newTestCaseResult.addProperty("Name", resultsName);
		newTestCaseResult.addProperty("Build", config.get("rally", "Build").trim());
		newTestCaseResult.addProperty("Date", nowAsISO);
		newTestCaseResult.addProperty("Verdict", verdict);

		newTestCaseResult.addProperty("TestCase", testCaseID);
		newTestCaseResult.addProperty("TestSet", testSetObjectID);

		newTestCaseResult.addProperty("Project",  config.get("rally", "ProjectID").trim());
		newTestCaseResult.addProperty("Notes", "Test Results are updated from Serenity Test Reports");

		// Post the testresults to Rally

		CreateRequest createRequest = new CreateRequest("TestCaseResult", newTestCaseResult);
		CreateResponse createResponse;
		try {
			createResponse = restApi.create(createRequest);
			Log.info(String.format("Created %s", createResponse.getObject().get("_ref").getAsString()));

		} catch (IOException e) {
			Log.info(e);

		}

	}

}
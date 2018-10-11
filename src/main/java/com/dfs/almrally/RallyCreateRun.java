package com.dfs.almrally;

import java.io.IOException;
import java.net.URISyntaxException;
import com.dfs.rally.rest.RallyRestApi;
import com.dfs.rally.rest.services.RestApiFactory;
import org.junit.After;
import org.junit.Before;
import serenity.results.parser.Jsonparser;

/**
 * <b>Description</b>This class updates the test results in Rally. <b>Date:</b>
 * 
 *   
 * @version 1.2.1
 *
 */
public class RallyCreateRun {
	RallyRestApi restApi;

	/**
	 * <b>Description</b>This method initializes the Rally rest API.
	 * 
	 * @throws IOException
	 */
	@Before
	public void initialize() throws IOException {
		// Release all resources
		try {
			restApi = RestApiFactory.getRestApi();
		} catch (URISyntaxException e) {

			throw new IOException(e);
		}
	}

	/**
	 * <b>Description</b>This method parses the report folder to read the result
	 * of each json file got generated for each feature file scenarios.
	 * 
	 * 
	 */
	public static void main(String[] args) throws IOException {

		Jsonparser runObj = new Jsonparser();
		runObj.getPOJOFromFeatureJson();
	}

	/**
	 * <b>Description</b>Releases the resources.
	 * 
	 * @throws IOException
	 */
	@After
	public void release() throws IOException {
		// Release all resources
		try {
			restApi.close();
		} catch (IOException e) {

			throw new IOException(e);
		}
	}
}
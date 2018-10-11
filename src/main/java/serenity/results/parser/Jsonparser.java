package serenity.results.parser;

import org.junit.Test;

import com.dfs.rally.rest.services.CucumberSetResult;
//import com.dfs.rally.rest.services.Sampletest;
//import com.dfs.rally.rest.services.TestResultsUpdated;
import jline.internal.Log;

/**
 * <b>Description:</b> This class converts feature file to json in Rally. <br>
 * <b>Methods</b>: getPOJOFromFeatureJson <br>
 * <b>Date:</b> August 24 2017
 * 
 
 * @version 1.2.1
 */
public class Jsonparser {

	/**
	 * <b>Description:</b> This method updates the test execution results in
	 * Rally.
	 */
	@Test
	public void getPOJOFromFeatureJson() {

		try {

			CucumberSetResult.createTestResults();
		} catch (Exception e) {

			Log.info(e);
		}
	}
}

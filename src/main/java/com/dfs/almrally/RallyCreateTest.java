package com.dfs.almrally;

import gherkin.feature.file.json.FeatureFileParsingTest;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import com.dfs.rally.rest.RallyRestApi;
import gherkin.feature.file.json.FindingFeatureFiles;
import jline.internal.Log;
import com.dfs.rally.rest.services.RestApiFactory;
import com.dfs.util.Config;

/** 
 * <b>Description</b>This class uploads the feature files as test cases in
 */
public class RallyCreateTest {

	RallyRestApi restApi;
	static Config config;
	

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
	 * <b>Description</b>This method parses the feature files based upon the
	 * property<br>
	 * in config ini  files.
	 * 
	 * 
	 * 
	 */
	public static void main(String[] args) throws IOException {

		FeatureFileParsingTest featureObj = new FeatureFileParsingTest();
		String featureFilePath =config.get("rally", "FeatureFilePath");
		Log.info("Feature file path: " + featureFilePath);
		if (featureFilePath.trim().endsWith(".feature")) {
			try {
				featureObj.getPOJOFromFeatureJson(featureFilePath);
			} catch (URISyntaxException e) {
				Log.info(e);
			}
		}

		else {
			// if we want only the feature files that contains the below
			// substring in it.
			String featurefileContains = config.get("rally","featurefileContains");
			if (featurefileContains == null)
				featurefileContains = "";
			List<File> featurefilesList = FindingFeatureFiles.getFeatureFiles(featureFilePath.trim(),
					featurefileContains.trim());
			Log.info("Feature Files size is" + featurefilesList.size());
			for (int i = 0; i < featurefilesList.size(); i++) {

				try {
					File f = featurefilesList.get(i);
					Log.info("file path: " + f.getPath());
					// pass this f var as an argument to the below method for
					// multiple feature files case.
					featureObj.getPOJOFromFeatureJson(f.getPath());
				} catch (URISyntaxException e) {

					Log.info(e);

				}
			}
		}

	}

	/**
	 * <b>Description</b>Releases the resources
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

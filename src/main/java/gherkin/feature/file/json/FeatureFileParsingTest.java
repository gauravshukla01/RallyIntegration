package gherkin.feature.file.json;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import org.junit.Test;
import com.dfs.util.FeatureFileToJsonConversion;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.dfs.almrally.feature.pojo.Feature;
import com.dfs.rally.rest.services.TestCase;
import com.dfs.rally.rest.services.TestPlanFolder;

import jline.internal.Log;

/**
 * <b>Description:</b> This class converts feature file to json and creates test folder and test cases in Rally. <br>
 * <b>Methods</b>: getPOJOFromFeatureJson  <br>
 * <b>Date:</b> August 24 2016
 * 
 
 * @version 1.2.1
 */
@SuppressWarnings("deprecation")
public class FeatureFileParsingTest {

	/**
	 * <b>Description:</b>creates test folder with feature name and test cases with scenario names in Rally. <br>
	 * @param fPath feature file path as a String
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@Test
	public void getPOJOFromFeatureJson(String fPath) throws URISyntaxException, IOException {
		
		String myFeaturFile = fPath;
		String outputJson = "src/test/java/RallyConvertedFeatureFile.json";
		FeatureFileToJsonConversion converter = new FeatureFileToJsonConversion("pretty");
		converter.convertFeatureFileToJson(myFeaturFile, outputJson);

		ObjectMapper mapper = new ObjectMapper();
		Feature f;

		try {
			f = mapper.readValue(new File(outputJson), Feature.class);
			Log.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(f));
			
			Log.info("# of ALL scenarios: " + f.elements.size());

			Log.info("----BEGIN :::   printing from POJO class--------------------");
			
			@SuppressWarnings("unused")
			String featureName = f.elements.get(0).keyword;
			Log.info(featureName);
			Log.info("Feature Name: " + f.name);

			// Validate Feature file name exists to create a new Folder in Test Plan
			String testPlanObject = TestPlanFolder.createTestPlanFolder(f);

			//Get the Feature level tags list from Feature file
			Log.info("Tags size: "+f.tags.size());
			StringBuilder tagBuilder = new StringBuilder();
			for (int tagcnt = 0; tagcnt < f.tags.size(); tagcnt++) {
				tagBuilder.append(f.tags.get(tagcnt).name);
				tagBuilder.append(" ");
			}
			String tags = "Feature file tags: "+"\n"+tagBuilder.toString();
			Log.info("Feature tags 1: "+tags);
			
			// Create a new Test Case under the created Test Folder
			for (int cnt = 0; cnt < f.elements.size(); cnt++) {
				Log.info("size of the element is " +f.elements.size());
				
				TestCase.createTestCase(f.elements.get(cnt), testPlanObject,tags);
			}

		} catch (IOException e) {
			Log.info(e);
		}
	}
}
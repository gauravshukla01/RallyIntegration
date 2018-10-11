package gherkin.feature.file.json;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
//import com.dfs.rally.rest.Constants;
import jline.internal.Log;

/**
 * <b>Description:</b>This class finds the feature file in particular package. <br>
 * <b>Methods</b>:getFeatureFiles,showFiles<br>
 * <b>Date:</b> August 24 2016
 * 
 
 * @version 1.2.1
 */

public class FindingFeatureFiles {
	
	static List<File> featurefilesList=new ArrayList<>();
	private FindingFeatureFiles()
	{
		//empty constructor
	}

	/**
	 * <b>Description:</b> This method fetches the feature files based upon substring value. <br>
	 * @param featureFilePath feature file path as a String
	 * @param featurefileContains
	 * @return number of feature files
	 */
	public static List<File> getFeatureFiles(String featureFilePath, String featurefileContains) {
		
			File[] files = new File(featureFilePath).listFiles();
		    
		    showFiles(files,featurefileContains);
		   Log.info("no of features file "+featurefilesList.size());
		    return featurefilesList;
		}

	/**
	 * <b>Description:</b>This method searches for a feature file in directory level. <br>
	 * @param files Array of feature files
	 * @param featurefileContains to validate whether feature file name contains required substring
	 */
		public static void showFiles(File[] files, String featurefileContains) {
		    for (File file : files) {
		        if (file.isDirectory()) {
		        	Log.info("Directory: " + file.getName());
		            showFiles(file.listFiles(),featurefileContains); // Calls same method again.
		        } else if(file.getName().endsWith(".feature")) {
		        	Log.info("File: " + file.getName());
		         
		            	// if they want to get specific feature files whose feature file name contains required substring 
		            	if(featurefileContains.length()>0 && (file.getName().contains(featurefileContains))){
		            		Log.info("inner if " + file.getName());
		            	featurefilesList.add(file);
		            	}
		            	else if(featurefileContains.length()==0){
		            	featurefilesList.add(file);
		            	}
		            }
		       }
		}
}

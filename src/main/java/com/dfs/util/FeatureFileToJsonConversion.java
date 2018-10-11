package com.dfs.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import gherkin.formatter.JSONFormatter;
import gherkin.formatter.JSONPrettyFormatter;
import gherkin.parser.Parser;
import gherkin.util.FixJava;
import jline.internal.Log;

/**
 * <b>Description:</b> This class converts Feature file to Json file.<br>
 * <b>Methods</b>: FeatureFileToJsonConversion, convertFeatureFileToJson <br>
 * <b>Date:</b> August 24 2017
 * 

 */
public class FeatureFileToJsonConversion {

	private String format;
	@SuppressWarnings("unused")
	long startTime = System.currentTimeMillis();

	public FeatureFileToJsonConversion(String outFormat) {
		this.format = outFormat;
	}

	@SuppressWarnings("unused")
	public String getOutFormat() {
		return format;
	}

	/**
	 * <b>Description:</b> This method is to parse and convert the Feature file
	 * to JSON format<br>
	 * 
	 * @param fPath
	 *            feature file path as a String
	 * @param jPath
	 *            json file creation path as a String
	 * @throws IOException
	 */

	public void convertFeatureFileToJson(String fPath, String jPath) throws IOException {

		String gherkin = null;
		FileInputStream fis = null;
		InputStreamReader isr = null;
		try {
			fis = new FileInputStream(fPath);
			isr = new InputStreamReader(fis, "UTF-8");
			gherkin = FixJava.readReader(isr);
		} catch (UnsupportedEncodingException | FileNotFoundException | RuntimeException e) {
			Log.info(e);
		}finally{
			if (fis != null)
				fis.close();
		}

		StringBuilder json = new StringBuilder();
		JSONFormatter formatter;
		if ("ugly".equalsIgnoreCase(format)) {
			formatter = new JSONFormatter(json);// not pretty
		} else {
			formatter = new JSONPrettyFormatter(json);// pretty
		}

		Parser parser = new Parser(formatter);
		parser.parse(gherkin, fPath, 0);
		formatter.done();
		formatter.close();
		String jsonResponse = json.toString();
		jsonResponse = jsonResponse.substring(1, jsonResponse.length() - 2);

		FileWriter file = null;
		try {
			file = new FileWriter(jPath);
			file.write(jsonResponse);
			file.flush();

		} catch (IOException e) {
			Log.info(e);
		} finally {
			if (file != null)
				file.close();
		}

	}

}

package com.dfs.util;

import java.io.*;
import org.ini4j.*;
import jline.internal.Log;

/**
 * <b>Description:</b> Config is a class which configures Config .ini file<br>
 * <b>Methods</b>: loadinifile, get <br>
 
 * 
 
 */
public class Config {

	static Ini configINI = null;

	/**
	 * <b>Description:</b> empty constructor<br>
	 */
	private Config() {

	}

	/**
	 * <b>Description</b> Loads Config .ini file and returns static ini.
	 * 
	 * @throws IOException
	 * @return configINI returns static ini
	 */
	private static Ini loadinifile() throws IOException {
		configINI = new Ini();
		try {
			InputStream file = Config.class.getResourceAsStream("/Config.ini");
			if (file == null)
				file = Config.class.getResourceAsStream("Config.ini");

			configINI.load(file);
		} catch (InvalidFileFormatException | FileNotFoundException e) {
			Log.info(e);
		}
		return configINI;
	}

	/**
	 * <b>Description</b> Loads Config .ini file and returns static ini.
	 * 
	 * @throws IOException
	 * @param sectionName
	 *            name of the section as a String
	 * @param keyName
	 *            name of the key as a String
	 * @return keyValue the value of key name
	 */
	public static synchronized String get(String sectionName, String keyName) throws IOException {
		if (configINI == null) {
			configINI = loadinifile();
		}

		String keyValue = configINI.get(sectionName, keyName);

		return (keyValue == null) ? "" : keyValue;
	}

	/**
	 * <b>Description</b> Loads Config .ini file and returns static ini.
	 * 
	
	 */
	public static synchronized String get(String sectionName, String keyName, String defaultValue) throws IOException {
		if (configINI == null) {
			configINI = loadinifile();
		}

		String keyValue = configINI.get(sectionName, keyName);

		return (keyValue == null || keyValue == "") ? defaultValue : keyValue;
	}
}
